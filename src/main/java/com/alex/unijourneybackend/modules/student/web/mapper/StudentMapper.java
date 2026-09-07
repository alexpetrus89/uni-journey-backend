package com.alex.unijourneybackend.modules.student.web.mapper;


import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.degree_course.web.dto.DegreeCourseDto;
import com.alex.unijourneybackend.modules.degree_course.web.mapper.DegreeCourseMapper;
import com.alex.unijourneybackend.modules.student.domain.model.Student;
import com.alex.unijourneybackend.modules.student.web.dto.response.StudentResponse;
import com.alex.unijourneybackend.modules.study_plan.web.dto.StudyPlanDto;
import com.alex.unijourneybackend.modules.study_plan.web.mapper.StudyPlanMapper;

@Component
public class StudentMapper {

    public StudentResponse toResponse(Student student) {

        DegreeCourseDto degreeCourse = student.getDegreeCourse() != null
            ? DegreeCourseMapper.toDto(student.getDegreeCourse())
            : null;

        StudyPlanDto studyPlan = student.getStudyPlan() != null
            ? StudyPlanMapper.toDto(student.getStudyPlan())
            : null;

        return new StudentResponse(
            student.getId().toString(),
            student.getUsername(),
            student.getFirstName(),
            student.getLastName(),
            student.getDob(),
            student.getAge(),
            student.getFiscalCode().fiscalCode(),
            student.getPhone(),
            student.getRegister().value(),
            student.getAddress().getStreet(),
            student.getAddress().getCity(),
            student.getAddress().getCountry(),
            student.getAddress().getZipCode(),
            degreeCourse,
            studyPlan
        );
    }
}

