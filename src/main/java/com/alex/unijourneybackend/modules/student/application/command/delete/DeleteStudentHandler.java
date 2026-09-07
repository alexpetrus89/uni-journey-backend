package com.alex.unijourneybackend.modules.student.application.command.delete;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.infrastructure.events.DomainEventPublisher;
import com.alex.unijourneybackend.modules.student.domain.model.Student;
import com.alex.unijourneybackend.modules.student.port.StudentRepositoryPort;
import com.alex.unijourneybackend.modules.user.application.bus.CommandHandler;
import com.alex.unijourneybackend.modules.user.application.command.support.AbstractDeletionApplicationService;
import com.alex.unijourneybackend.modules.user.application.command.support.DeletionResponseFactory;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;
import com.alex.unijourneybackend.modules.user.web.dto.response.DeletionResponse;

@Component
public class DeleteStudentHandler
    extends AbstractDeletionApplicationService
    implements CommandHandler<DeleteStudentCommand, DeletionResponse>{

    private final StudentRepositoryPort port;

    public DeleteStudentHandler(
        StudentRepositoryPort port,
        DomainEventPublisher publisher,
        DeletionResponseFactory factory
    ) {
        super(publisher, factory);
        this.port = port;
    }

    @Override
    @Transactional
    public DeletionResponse handle(DeleteStudentCommand command) {

        return withDataAccessHandling(
            "Database error while deleting student with id '%s'.".formatted(command.toString()),
            () -> {
                Student student = findStudent(command.id());
                Objects.requireNonNull(student, "Student cannot be null");
                port.delete(student);
                return buildDeletionResponse(student, student.getRegister().value());
            }
        );
    }


    private Student findStudent(UserId id) {
        return withDataAccessHandling(
            "Database error while fetching student by id '%s'.".formatted(id.toString()),
            () -> port
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student with id '%s' not found.".formatted(id.toString())))
        );
    }
}