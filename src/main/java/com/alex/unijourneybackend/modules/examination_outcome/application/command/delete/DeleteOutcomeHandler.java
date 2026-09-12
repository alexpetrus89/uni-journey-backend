package com.alex.unijourneybackend.modules.examination_outcome.application.command.delete;


import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.modules.examination_outcome.domain.model.ExaminationOutcome;
import com.alex.unijourneybackend.modules.examination_outcome.port.ExaminationOutcomeRepositoryPort;
import com.alex.unijourneybackend.modules.student.domain.model.Student;
import com.alex.unijourneybackend.modules.student.domain.service.StudentResolver;

/**
 * Removes an outcome. Used both to confirm an insufficient/absent/failed
 * result and to refuse a passing grade: in both cases the outcome simply
 * stops existing and the appeal is no longer pending for the student.
 */
@Service
public class DeleteOutcomeHandler {

    private final ExaminationOutcomeRepositoryPort port;
    private final StudentResolver resolver;

    public DeleteOutcomeHandler(ExaminationOutcomeRepositoryPort port, StudentResolver resolver) {
        this.port = port;
        this.resolver = resolver;
    }

    @Transactional
    public void handle(DeleteOutcomeCommand command) {
        Objects.requireNonNull(command.id(), "id must not be null");

        Student student = resolver.resolveByUsername(command.username());

        ExaminationOutcome outcome = port
            .findById(Objects.requireNonNull(command.id(), "id must not be null"))
            .orElseThrow(() -> new NoSuchElementException("Examination outcome with id '%s' not found.".formatted(command.id())));

        if (!outcome.getRegister().equals(student.getRegister().toString()))
            throw new AccessDeniedException("This outcome does not belong to the authenticated student");

        port.delete(outcome);
    }


}
