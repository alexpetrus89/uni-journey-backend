package com.alex.unijourneybackend.modules.student.application.command.register;

import java.util.Objects;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.common.domain.exception.DataAccessServiceException;
import com.alex.unijourneybackend.infrastructure.events.DomainEventPublisher;
import com.alex.unijourneybackend.modules.student.domain.model.Student;
import com.alex.unijourneybackend.modules.student.domain.service.RegisterGenerator;
import com.alex.unijourneybackend.modules.student.domain.service.StudentFactory;
import com.alex.unijourneybackend.modules.student.domain.valueobject.Register;
import com.alex.unijourneybackend.modules.student.port.StudentRepositoryPort;
import com.alex.unijourneybackend.modules.user.application.bus.CommandHandler;
import com.alex.unijourneybackend.modules.user.application.command.support.AbstractRegistrationApplicationService;
import com.alex.unijourneybackend.modules.user.application.command.support.RegistrationResponseFactory;
import com.alex.unijourneybackend.modules.user.web.dto.response.RegistrationResponse;

@Service
public class RegisterStudentHandler
    extends AbstractRegistrationApplicationService
    implements CommandHandler<RegisterStudentCommand, RegistrationResponse> {

    private static final int MAX_STUDENT_REGISTER_GENERATION_ATTEMPTS = 5;

    private final StudentRepositoryPort port;
    private final StudentFactory factory;
    private final RegisterGenerator generator;

    public RegisterStudentHandler(
        StudentRepositoryPort port,
        StudentFactory factory,
        RegisterGenerator generator,
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
    public RegistrationResponse handle(RegisterStudentCommand command) {

        Register register = withDataAccessHandling(
            "Database error while generating student register",
            this::generateStudentRegister
        );

        Student student = factory.create(command, null, command.ordering(), register);

        return withDataAccessHandling(
            "Database error while registering student '%s'.".formatted(command.username()),
            () -> {
                Objects.requireNonNull(student, "Student cannot be null");
                Student savedStudent = port.create(student);
                publishUserRegisteredEvent(savedStudent);
                return buildRegistrationResponse(savedStudent, savedStudent.getRegister().value());
            }
        );
    }


    private Register generateStudentRegister() {
        for (int attempt = 0; attempt < MAX_STUDENT_REGISTER_GENERATION_ATTEMPTS; attempt++) {
            Register candidate = generator.generate();
            Objects.requireNonNull(candidate, "Generated register cannot be null");

            if (!port.existsByRegister(candidate)) return candidate;
        }

        throw new DataAccessServiceException("Unable to generate a unique student code after multiple attempts.");
    }


}
