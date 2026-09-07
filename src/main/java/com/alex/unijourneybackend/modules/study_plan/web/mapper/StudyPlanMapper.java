package com.alex.unijourneybackend.modules.study_plan.web.mapper;

import java.util.Comparator;
import java.util.List;

import com.alex.unijourneybackend.modules.course.domain.model.Course;
import com.alex.unijourneybackend.modules.study_plan.domain.model.StudyPlan;
import com.alex.unijourneybackend.modules.study_plan.web.dto.StudyPlanDto;



public final class StudyPlanMapper {

    private StudyPlanMapper() {}

    @SuppressWarnings("null")
    public static StudyPlanDto toDto(StudyPlan studyPlan) {
        List<StudyPlanDto.CourseDto> courses = studyPlan.getCourses()
            .stream()
            .sorted(Comparator.comparing(Course::getYearOfStudy)
                .thenComparing(Course::getName))
            .map(StudyPlanMapper::toCourseDto)
            .toList();

        return new StudyPlanDto(studyPlan.getOrdering(), courses);
    }

    @SuppressWarnings("null")
    public static StudyPlanDto.CourseDto toCourseDto(Course course) {
        return new StudyPlanDto.CourseDto(
            course.getName(),
            course.getDegreeCourse() != null ? course.getDegreeCourse().getName() : null,
            course.getCfu(),
            course.getYearOfStudy(),
            course.isMandatoryCourse(),
            course.getType().name()
        );
    }

}
