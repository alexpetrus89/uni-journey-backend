package com.alex.unijourneybackend.modules.admin.application.command.register;

import java.util.Objects;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.common.domain.exception.DataAccessServiceException;
import com.alex.unijourneybackend.infrastructure.events.DomainEventPublisher;
import com.alex.unijourneybackend.modules.admin.application.factory.AdminFactory;
import com.alex.unijourneybackend.modules.admin.domain.model.Admin;
import com.alex.unijourneybackend.modules.admin.domain.service.AdminCodeGenerator;
import com.alex.unijourneybackend.modules.admin.domain.valueobject.AdminCode;
import com.alex.unijourneybackend.modules.admin.port.AdminRepositoryPort;
import com.alex.unijourneybackend.modules.user.application.bus.CommandHandler;
import com.alex.unijourneybackend.modules.user.application.command.support.AbstractRegistrationApplicationService;
import com.alex.unijourneybackend.modules.user.application.command.support.RegistrationResponseFactory;
import com.alex.unijourneybackend.modules.user.web.dto.response.RegistrationResponse;

@Service
public class RegisterAdminHandler
    extends AbstractRegistrationApplicationService
    implements CommandHandler<RegisterAdminCommand, RegistrationResponse> {

    private static final int MAX_ADMIN_CODE_GENERATION_ATTEMPTS = 5;

    private final AdminRepositoryPort port;
    private final AdminFactory factory;
    private final AdminCodeGenerator generator;

    public RegisterAdminHandler(
        AdminRepositoryPort port,
        AdminFactory factory,
        AdminCodeGenerator generator,
        DomainEventPublisher publisher,
        RegistrationResponseFactory responseFactory
    ) {
        super(publisher, responseFactory);
        this.port = port;
        this.factory = factory;
        this.generator = generator;
    }


    @Override
    @Transactional(rollbackFor = DataAccessServiceException.class)
    @Retryable(
        retryFor = DataAccessServiceException.class,
        maxAttempts = 3,
        backoff = @Backoff(delay = 1000)
    )
    public RegistrationResponse handle(RegisterAdminCommand command) {

        // 1. generazione codice admin univoco
        AdminCode code = withDataAccessHandling(
            "Database error while generating admin code",
            this::generateUniqueAdminCode
        );

        // 2. creazione entità in memoria — nessun accesso al DB, fuori dal wrapper
        Admin admin = factory.create(command, code);

        // 3. persistenza e pubblicazione evento
        return withDataAccessHandling(
            "Database error while registering admin '%s'".formatted(command.username()),
            () -> {
                Objects.requireNonNull(admin, "admin cannot be null");
                Admin savedAdmin = port.create(admin);
                publishUserRegisteredEvent(savedAdmin);
                return buildRegistrationResponse(savedAdmin, savedAdmin.getAdminCode().value());
            }
        );
    }


    private AdminCode generateUniqueAdminCode() {
        for (int attempt = 0; attempt < MAX_ADMIN_CODE_GENERATION_ATTEMPTS; attempt++) {
            AdminCode candidate = generator.generate();
            Objects.requireNonNull(candidate, "Generated admin code cannot be null");

            if (!port.existsByAdminCode(candidate)) return candidate;
        }

        throw new DataAccessServiceException("Unable to generate a unique admin code after multiple attempts.");
    }


}
