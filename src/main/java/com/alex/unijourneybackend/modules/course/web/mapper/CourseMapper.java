package com.alex.unijourneybackend.modules.course.web.mapper;

import com.alex.unijourneybackend.modules.course.domain.model.Course;
import com.alex.unijourneybackend.modules.course.web.dto.CourseDto;
import com.alex.unijourneybackend.modules.degree_course.web.mapper.DegreeCourseMapper;
import com.alex.unijourneybackend.modules.professor.web.mapper.ProfessorDtoMapper;

public class CourseMapper {

    private CourseMapper() {} // private constructor to prevent instantiation


    public static CourseDto toDto(Course course) {
        if(course == null) return null;
        return new CourseDto(
            course.getName(),
            course.getType(),
            course.getCfu(),
            course.getYearOfStudy(),
            course.isMandatoryCourse(),
            ProfessorDtoMapper.toDto(course.getProfessor()),
            DegreeCourseMapper.toDto(course.getDegreeCourse())
        );
    }

}
