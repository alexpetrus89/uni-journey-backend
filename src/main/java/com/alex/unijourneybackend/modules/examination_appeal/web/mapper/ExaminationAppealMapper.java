package com.alex.unijourneybackend.modules.examination_appeal.web.mapper;

import java.util.Set;

import com.alex.unijourneybackend.modules.examination_appeal.domain.model.ExaminationAppeal;
import com.alex.unijourneybackend.modules.examination_appeal.web.dto.ExaminationAppealDto;
import com.alex.unijourneybackend.modules.student.web.dto.request.StudentRequest;

public final class ExaminationAppealMapper {

    private ExaminationAppealMapper() {}

    public static ExaminationAppealDto toDto(ExaminationAppeal appeal, Set<StudentRequest> students) {
        if (appeal == null) return null;

        ExaminationAppealDto dto = new ExaminationAppealDto();
        dto.setId(appeal.getId());
        dto.setCourse(appeal.getCourse().getName());
        dto.setDegreeCourse(appeal.getCourse().getDegreeCourse().getName());
        dto.setCourseCfu(appeal.getCourse().getCfu().toString());
        dto.setProfessorCode(appeal.getProfessorCode().toString());
        dto.setDescription(appeal.getDescription());
        dto.setDate(appeal.getDate());
        dto.setStudents(students);

        return dto;
    }




}

