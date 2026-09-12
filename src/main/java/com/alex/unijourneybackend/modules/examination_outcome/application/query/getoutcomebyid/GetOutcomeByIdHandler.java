package com.alex.unijourneybackend.modules.examination_outcome.application.query.getoutcomebyid;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.modules.examination_outcome.domain.model.ExaminationOutcome;
import com.alex.unijourneybackend.modules.examination_outcome.port.ExaminationOutcomeRepositoryPort;
import com.alex.unijourneybackend.modules.examination_outcome.web.dto.ExaminationOutcomeDto;
import com.alex.unijourneybackend.modules.examination_outcome.web.mapper.ExaminationOutcomeMapper;
import com.alex.unijourneybackend.modules.student.domain.model.Student;
import com.alex.unijourneybackend.modules.student.domain.service.StudentResolver;

@Service
public class GetOutcomeByIdHandler {

    private final ExaminationOutcomeRepositoryPort port;
    private final StudentResolver resolver;

    public GetOutcomeByIdHandler(ExaminationOutcomeRepositoryPort port, StudentResolver resolver) {
        this.port = port;
        this.resolver = resolver;
    }

    @Transactional(readOnly = true)
    public ExaminationOutcomeDto handle(GetOutcomeByIdQuery query) {
        Objects.requireNonNull(query.id(), "id must not be null");

        Student student = resolver.resolveByUsername(query.username());

        ExaminationOutcome outcome = port
            .findById(Objects.requireNonNull(query.id(), "id must not be null"))
            .orElseThrow(() -> new NoSuchElementException("Examination outcome with id '%s' not found.".formatted(query.id())));

        assertOwnedByStudent(outcome, student);

        return ExaminationOutcomeMapper.toDto(outcome);
    }

    private void assertOwnedByStudent(ExaminationOutcome outcome, Student student) {
        if (!outcome.getRegister().equals(student.getRegister().toString()))
            throw new AccessDeniedException("This outcome does not belong to the authenticated student");
    }


}
