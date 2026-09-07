package com.alex.unijourneybackend.modules.student.web.dto.response;

import java.time.LocalDate;

import com.alex.unijourneybackend.modules.degree_course.web.dto.DegreeCourseDto;
import com.alex.unijourneybackend.modules.study_plan.web.dto.StudyPlanDto;

public record StudentResponse(
    String id,
    String username,
    String firstName,
    String lastName,
    LocalDate dob,
    Integer age,
    String fiscalCode,
    String phone,
    String register,
    String street,
    String city,
    String country,
    String zip,
    DegreeCourseDto degreeCourse,
    StudyPlanDto studyPlan
) {}

