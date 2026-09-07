package com.alex.unijourneybackend.modules.professor.web.mapper;

import com.alex.unijourneybackend.modules.professor.domain.model.Professor;
import com.alex.unijourneybackend.modules.professor.web.dto.ProfessorDto;

public class ProfessorDtoMapper {

    private ProfessorDtoMapper() {} // private constructor to prevent instantiation


    public static ProfessorDto toDto(Professor professor) {
        if(professor == null) return null;
        return new ProfessorDto(
            professor.getUsername(),
            professor.getFirstName(),
            professor.getLastName(),
            professor.getFiscalCode().toString(),
            professor.getProfessorCode().toString()
        );
    }

}
