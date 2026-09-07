package com.alex.unijourneybackend.modules.course.application.query.degree_course;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.course.domain.model.Course;
import com.alex.unijourneybackend.modules.course.port.CourseRepositoryPort;
import com.alex.unijourneybackend.modules.course.web.dto.CourseDto;
import com.alex.unijourneybackend.modules.course.web.mapper.CourseMapper;

@Service
public class GetCoursesHandler {

    private final CourseRepositoryPort port;

    public GetCoursesHandler(CourseRepositoryPort port) {
        this.port = port;
    }

    public PageResult<CourseDto> handle(GetCoursesQuery query, String name) {
        PageQuery page = Objects.requireNonNull(query.pageQuery(), "pageQuery must not be null");

        Objects.requireNonNull(name, "degree course name must not be null");
        PageResult<Course> result = port.findByDegreeCourseName(page, name);

        List<CourseDto> dtos = result
            .content()
            .stream()
            .map(CourseMapper::toDto) // metodo statico, niente iniezione
            .toList();

        return new PageResult<>(dtos, result.totalElements());
    }


}
