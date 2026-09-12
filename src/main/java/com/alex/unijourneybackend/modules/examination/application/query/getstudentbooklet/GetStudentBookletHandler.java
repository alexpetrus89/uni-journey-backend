package com.alex.unijourneybackend.modules.examination.application.query.getstudentbooklet;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.modules.examination.port.ExaminationRepositoryPort;
import com.alex.unijourneybackend.modules.examination.web.dto.response.ExaminationResponse;
import com.alex.unijourneybackend.modules.examination.web.mapper.ExaminationMapper;
import com.alex.unijourneybackend.modules.student.domain.model.Student;
import com.alex.unijourneybackend.modules.student.domain.service.StudentResolver;

@Service
public class GetStudentBookletHandler {

    private final ExaminationRepositoryPort port;
    private final StudentResolver resolver;

    public GetStudentBookletHandler(ExaminationRepositoryPort port, StudentResolver resolver) {
        this.port = port;
        this.resolver = resolver;
    }

    @Transactional(readOnly = true)
    public List<ExaminationResponse> handle(GetStudentBookletQuery query) {
        Student student = resolver.resolveByUsername(query.username());

        return port
            .findByRegister(Objects.requireNonNull(student.getRegister().toString(), "Student register must not be null"))
            .stream()
            .map(ExaminationMapper::toResponse)
            .toList();
    }


}
