package com.alex.unijourneybackend.modules.professor.web.mapper;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.professor.domain.model.Professor;
import com.alex.unijourneybackend.modules.professor.web.dto.ProfessorResponse;

@Component
public class ProfessorMapper {

    public ProfessorResponse toResponse(Professor professor) {
        return new ProfessorResponse(
            professor.getId().toString(),
            professor.getUsername(),
            professor.getFirstName(),
            professor.getLastName(),
            professor.getDob(),
            professor.getAge(),
            professor.getFiscalCode().fiscalCode(),
            professor.getPhone(),
            professor.getProfessorCode().value(),
            professor.getAddress().getStreet(),
            professor.getAddress().getCity(),
            professor.getAddress().getCountry(),
            professor.getAddress().getZipCode()
        );
    }
}
