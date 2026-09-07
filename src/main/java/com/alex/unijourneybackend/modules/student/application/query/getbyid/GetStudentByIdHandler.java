package com.alex.unijourneybackend.modules.student.application.query.getbyid;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.modules.student.domain.model.Student;
import com.alex.unijourneybackend.modules.student.port.StudentRepositoryPort;
import com.alex.unijourneybackend.modules.student.web.dto.response.StudentResponse;
import com.alex.unijourneybackend.modules.student.web.mapper.StudentMapper;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;

@Component
public class GetStudentByIdHandler {
    private final StudentRepositoryPort port;
    private final StudentMapper mapper;

    public GetStudentByIdHandler(StudentRepositoryPort port, StudentMapper mapper) {
        this.port = port;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public StudentResponse handle(GetStudentByIdQuery query) {

        UserId id = query.id();
        Objects.requireNonNull(id, "id cannot be null");

        Student student = port
            .findById(id)
            .orElseThrow(() -> new NoSuchElementException("Student not found"));

        return mapper.toResponse(student);
    }


}