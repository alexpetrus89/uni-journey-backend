package com.alex.unijourneybackend.modules.examination_outcome.application.query.getstudentoutcomes;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.modules.examination_outcome.port.ExaminationOutcomeRepositoryPort;
import com.alex.unijourneybackend.modules.examination_outcome.web.dto.ExaminationOutcomeDto;
import com.alex.unijourneybackend.modules.examination_outcome.web.mapper.ExaminationOutcomeMapper;
import com.alex.unijourneybackend.modules.student.domain.model.Student;
import com.alex.unijourneybackend.modules.student.domain.service.StudentResolver;

@Service
public class GetStudentOutcomesHandler {

    private final ExaminationOutcomeRepositoryPort port;
    private final StudentResolver resolver;

    public GetStudentOutcomesHandler(ExaminationOutcomeRepositoryPort port, StudentResolver resolver) {
        this.port = port;
        this.resolver = resolver;
    }

    @Transactional(readOnly = true)
    public List<ExaminationOutcomeDto> handle(GetStudentOutcomesQuery query) {
        Student student = resolver.resolveByUsername(query.username());

        return port
            .findByRegister(Objects.requireNonNull(student.getRegister().toString(), "Student register must not be null"))
            .stream()
            .map(ExaminationOutcomeMapper::toDto)
            .toList();
    }


}