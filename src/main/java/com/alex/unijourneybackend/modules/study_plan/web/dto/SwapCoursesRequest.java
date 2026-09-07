package com.alex.unijourneybackend.modules.study_plan.web.dto;

import com.alex.unijourneybackend.common.validation.annotation.SwapCoursesConstraint;

import jakarta.validation.constraints.NotBlank;

@SwapCoursesConstraint
public record SwapCoursesRequest(

    @NotBlank(message = "course to add must not be blank")
    String courseToAdd,

    @NotBlank(message = "degree course of new course must not be blank")
    String degreeCourseOfNewCourse,

    @NotBlank(message = "course to remove must not be blank")
    String courseToRemove,

    @NotBlank(message = "degree course of old course must not be blank")
    String degreeCourseOfOldCourse

) {}
