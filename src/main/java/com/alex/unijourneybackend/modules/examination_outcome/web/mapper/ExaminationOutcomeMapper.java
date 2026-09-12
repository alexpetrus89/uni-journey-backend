package com.alex.unijourneybackend.modules.examination_outcome.web.mapper;


import java.util.Set;

import com.alex.unijourneybackend.modules.examination_appeal.web.dto.ExaminationAppealDto;
import com.alex.unijourneybackend.modules.examination_appeal.web.mapper.ExaminationAppealMapper;
import com.alex.unijourneybackend.modules.examination_outcome.domain.model.ExaminationOutcome;
import com.alex.unijourneybackend.modules.examination_outcome.web.dto.ExaminationOutcomeDto;
import com.alex.unijourneybackend.modules.professor.domain.model.Professor;
import com.alex.unijourneybackend.modules.student.web.dto.request.StudentRequest;

public final class ExaminationOutcomeMapper {

    private ExaminationOutcomeMapper() {}

    public static ExaminationOutcomeDto toDto(ExaminationOutcome outcome) {
        if (outcome == null) return null;

        ExaminationAppealDto appealDto = ExaminationAppealMapper.toDto(outcome.getAppeal(), Set.<StudentRequest>of());
        Professor professor = outcome.getAppeal().getCourse().getProfessor();
        appealDto.setProfessorFullName(
            professor != null ? professor.getFirstName() + " " + professor.getLastName() : null
        );

        ExaminationOutcomeDto dto = new ExaminationOutcomeDto(outcome.getId(), appealDto, outcome.getRegister());
        dto.setGrade(outcome.getGrade());
        dto.setPresent(outcome.isPresent());
        dto.setWithHonors(outcome.isWithHonors());
        dto.setAccepted(outcome.isAccepted());
        dto.setMessage(outcome.getMessage());

        return dto;
    }


}

