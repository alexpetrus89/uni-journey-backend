package com.alex.unijourneybackend.modules.study_plan.web.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StudyPlanDto(

    @NotBlank(message = "Ordering must not be blank")
    String ordering,

    @NotNull(message = "Courses must not be null")
    @Size(min = 1, message = "At least one course must be selected")
    List<CourseDto> courses
) {

    public record CourseDto(
        String name,
        String degreeCourse,
        Integer cfu,
        Integer yearOfStudy,
        boolean mandatoryCourse,
        String type
    ) {}

}
