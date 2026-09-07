package com.alex.unijourneybackend.modules.admin.application.command.delete;

import com.alex.unijourneybackend.modules.user.application.command.support.DeletionResponseFactory;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.infrastructure.events.DomainEventPublisher;
import com.alex.unijourneybackend.modules.admin.domain.model.Admin;
import com.alex.unijourneybackend.modules.admin.port.AdminRepositoryPort;
import com.alex.unijourneybackend.modules.user.application.bus.CommandHandler;
import com.alex.unijourneybackend.modules.user.application.command.support.AbstractDeletionApplicationService;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;
import com.alex.unijourneybackend.modules.user.web.dto.response.DeletionResponse;


@Service
public class DeleteAdminHandler
    extends AbstractDeletionApplicationService
    implements CommandHandler<DeleteAdminCommand, DeletionResponse> {

    private final AdminRepositoryPort port;

    public DeleteAdminHandler(
        AdminRepositoryPort port,
        DomainEventPublisher publisher,
        DeletionResponseFactory factory
    ) {
        super(publisher, factory);
        this.port = port;
    }

    @Override
    @Transactional
    public DeletionResponse handle(DeleteAdminCommand command) {

        return withDataAccessHandling(
            "Database error while deleting admin with id '%s'.".formatted(command.toString()),
            () -> {
                Admin admin = findAdmin(command.id());
                Objects.requireNonNull(admin, "admin cannot be null");
                port.delete(admin);
                return buildDeletionResponse(admin, admin.getAdminCode().value());
            }
        );
    }


    private Admin findAdmin(UserId id) {
        return withDataAccessHandling(
            "Database error while fetching admin by id '%s'.".formatted(id.toString()),
            () -> port
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Admin with id '%s' not found.".formatted(id.toString())))
        );
    }


}
