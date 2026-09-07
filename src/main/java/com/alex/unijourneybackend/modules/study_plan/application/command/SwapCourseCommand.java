package com.alex.unijourneybackend.modules.study_plan.application.command;

import com.alex.unijourneybackend.modules.study_plan.web.dto.response.SwapCourseResponse;
import com.alex.unijourneybackend.modules.user.application.bus.Command;

public record SwapCourseCommand(
    String username,
    String courseToRemove,
    String degreeCourseOfOldCourse,
    String courseToAdd,
    String degreeCourseOfNewCourse
) implements Command<SwapCourseResponse> {}
