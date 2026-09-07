package com.alex.unijourneybackend.common.validation.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.common.validation.annotation.SwapCoursesConstraint;
import com.alex.unijourneybackend.modules.course.domain.model.Course;
import com.alex.unijourneybackend.modules.course.port.CourseRepositoryPort;
import com.alex.unijourneybackend.modules.study_plan.web.dto.SwapCoursesRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class SwapCoursesValidator implements ConstraintValidator<SwapCoursesConstraint, SwapCoursesRequest> {

    private static final String COURSE_TO_ADD = "courseToAdd";
    private static final String COURSE_TO_REMOVE = "courseToRemove";

    private final CourseRepositoryPort port;

    public SwapCoursesValidator(CourseRepositoryPort port) {
        this.port = port;
    }

    @Override
    public boolean isValid(SwapCoursesRequest request, ConstraintValidatorContext context) {
        if (request == null) return false;

        context.disableDefaultConstraintViolation();

        boolean valid = true;

        valid &= checkSameCourse(request, context);
        valid &= checkCoursesExist(request, context);

        return valid;
    }


    // private methods
    private boolean checkSameCourse(SwapCoursesRequest request, ConstraintValidatorContext context) {
        if (request.courseToAdd() != null &&
            request.courseToRemove() != null &&
            request.courseToAdd().equalsIgnoreCase(request.courseToRemove())) {

            context
                .buildConstraintViolationWithTemplate("You cannot replace a course with itself")
                .addPropertyNode(COURSE_TO_ADD)
                .addConstraintViolation();
            return false;
        }
        return true;
    }


    private boolean checkCoursesExist(SwapCoursesRequest request, ConstraintValidatorContext context) {
        if (request.courseToAdd() == null || request.courseToRemove() == null ||
            request.degreeCourseOfNewCourse() == null || request.degreeCourseOfOldCourse() == null)
            return false; // skip further checks if data is incomplete

        String courseToRemoveName = Objects.requireNonNull(request.courseToRemove(), "courseToRemove must not be null");
        String courseToAddName = Objects.requireNonNull(request.courseToAdd(), "courseToAdd must not be null");
        String degreeCourseOfOldCourse = Objects.requireNonNull(request.degreeCourseOfOldCourse(), "Degree Course of old course must not be null");
        String degreeCourseOfNewCourse = Objects.requireNonNull(request.degreeCourseOfNewCourse(), "Degree Course of new course must not be null");


        var courseToAddOpt = port.findByNameAndDegreeCourseName(courseToAddName, degreeCourseOfNewCourse);
        var courseToRemoveOpt = port.findByNameAndDegreeCourseName(courseToRemoveName, degreeCourseOfOldCourse);

        boolean valid = true;

        if (courseToAddOpt.isEmpty()) {
            context
                .buildConstraintViolationWithTemplate("New course not found")
                .addPropertyNode(COURSE_TO_ADD)
                .addConstraintViolation();
            valid = false;
        }

        if (courseToRemoveOpt.isEmpty()) {
            context
                .buildConstraintViolationWithTemplate("Old course not found")
                .addPropertyNode(COURSE_TO_REMOVE)
                .addConstraintViolation();
            valid = false;
        }

        if (courseToAddOpt.isPresent() && courseToRemoveOpt.isPresent())
            valid &= checkCfu(courseToAddOpt.get(), courseToRemoveOpt.get(), context);

        return valid;
    }


    private boolean checkCfu(Course courseToAdd, Course courseToRemove, ConstraintValidatorContext context) {
        if (!courseToAdd.getCfu().equals(courseToRemove.getCfu())) {
            context
                .buildConstraintViolationWithTemplate("The new course must have the same number of CFU as the course to be replaced")
                .addPropertyNode(COURSE_TO_ADD)
                .addConstraintViolation();
            return false;
        }
        return true;
    }


}
