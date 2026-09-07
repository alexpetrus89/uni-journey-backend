package com.alex.unijourneybackend.modules.professor.application.command.delete;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.infrastructure.events.DomainEventPublisher;
import com.alex.unijourneybackend.modules.professor.domain.model.Professor;
import com.alex.unijourneybackend.modules.professor.port.ProfessorRepositoryPort;
import com.alex.unijourneybackend.modules.user.application.bus.CommandHandler;
import com.alex.unijourneybackend.modules.user.application.command.support.AbstractDeletionApplicationService;
import com.alex.unijourneybackend.modules.user.application.command.support.DeletionResponseFactory;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;
import com.alex.unijourneybackend.modules.user.web.dto.response.DeletionResponse;

@Component
public class DeleteProfessorHandler
    extends AbstractDeletionApplicationService
    implements CommandHandler<DeleteProfessorCommand, DeletionResponse> {

    private final ProfessorRepositoryPort port;

    public DeleteProfessorHandler(
        ProfessorRepositoryPort port,
        DomainEventPublisher publisher,
        DeletionResponseFactory factory
    ) {
        super(publisher, factory);
        this.port = port;
    }

    @Override
    @Transactional
    public DeletionResponse handle(DeleteProfessorCommand command) {

        return withDataAccessHandling(
            "Database error while deleting professor with id '%s'.".formatted(command.toString()),
            () -> {
                Professor professor = findProfessor(command.id());
                Objects.requireNonNull(professor, "Professor cannot be null");
                port.delete(professor);
                return buildDeletionResponse(professor, professor.getProfessorCode().value());
            }
        );
    }


    private Professor findProfessor(UserId id) {
        return withDataAccessHandling(
            "Database error while fetching professor by id '%s'.".formatted(id.toString()),
            () -> port
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Professor with id '%s' not found.".formatted(id.toString())))
        );
    }


}

