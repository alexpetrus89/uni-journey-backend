package com.alex.unijourneybackend.modules.professor.application.query.getbyid;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.modules.professor.domain.model.Professor;
import com.alex.unijourneybackend.modules.professor.port.ProfessorRepositoryPort;
import com.alex.unijourneybackend.modules.professor.web.dto.ProfessorResponse;
import com.alex.unijourneybackend.modules.professor.web.mapper.ProfessorMapper;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;


@Component
public class GetProfessorByIdHandler {

    private final ProfessorRepositoryPort port;
    private final ProfessorMapper mapper;

    public GetProfessorByIdHandler(ProfessorRepositoryPort port, ProfessorMapper mapper) {
        this.port = port;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public ProfessorResponse handle(GetProfessorByIdQuery query) {

        UserId id = query.id();
        Objects.requireNonNull(id, "id cannot be null");

        Professor professor = port
            .findById(id)
            .orElseThrow(() -> new NoSuchElementException("Professor not found"));

        return mapper.toResponse(professor);
    }


}
