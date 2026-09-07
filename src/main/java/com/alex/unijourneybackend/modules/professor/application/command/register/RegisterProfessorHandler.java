package com.alex.unijourneybackend.modules.professor.application.command.register;

import java.util.Objects;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.common.domain.exception.DataAccessServiceException;
import com.alex.unijourneybackend.infrastructure.events.DomainEventPublisher;
import com.alex.unijourneybackend.modules.professor.domain.model.Professor;
import com.alex.unijourneybackend.modules.professor.domain.service.ProfessorCodeGenerator;
import com.alex.unijourneybackend.modules.professor.domain.service.ProfessorFactory;
import com.alex.unijourneybackend.modules.professor.domain.valueobject.ProfessorCode;
import com.alex.unijourneybackend.modules.professor.port.ProfessorRepositoryPort;
import com.alex.unijourneybackend.modules.user.application.bus.CommandHandler;
import com.alex.unijourneybackend.modules.user.application.command.support.AbstractRegistrationApplicationService;
import com.alex.unijourneybackend.modules.user.application.command.support.RegistrationResponseFactory;
import com.alex.unijourneybackend.modules.user.web.dto.response.RegistrationResponse;


@Service
public class RegisterProfessorHandler
    extends AbstractRegistrationApplicationService
    implements CommandHandler<RegisterProfessorCommand, RegistrationResponse> {

    private static final int MAX_PROFESSOR_CODE_GENERATION_ATTEMPTS = 5;

    private final ProfessorRepositoryPort port;
    private final ProfessorFactory factory;
    private final ProfessorCodeGenerator generator;

    public RegisterProfessorHandler(
        ProfessorRepositoryPort port,
        ProfessorFactory factory,
        ProfessorCodeGenerator generator,
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
    public RegistrationResponse handle(RegisterProfessorCommand command) {

        ProfessorCode code = withDataAccessHandling(
            "Database error while generating professor code",
            this::generateUniqueProfessorCode
        );

        Professor professor = factory.create(command, code);

        return withDataAccessHandling(
            "Database error while registering professor '%s'.".formatted(command.username()),
            () -> {
                Objects.requireNonNull(professor, "Professor cannot be null");
                Professor savedProfessor = port.create(professor);
                publishUserRegisteredEvent(savedProfessor);
                return buildRegistrationResponse(savedProfessor, savedProfessor.getProfessorCode().value());
            }
        );
    }


    private ProfessorCode generateUniqueProfessorCode() {
        for (int attempt = 0; attempt < MAX_PROFESSOR_CODE_GENERATION_ATTEMPTS; attempt++) {
            ProfessorCode candidate = generator.generate();
            Objects.requireNonNull(candidate, "Generated professor code cannot be null");

            if (!port.existsByProfessorCode(candidate)) return candidate;
        }

        throw new DataAccessServiceException("Unable to generate a unique professor code after multiple attempts.");
    }


}
