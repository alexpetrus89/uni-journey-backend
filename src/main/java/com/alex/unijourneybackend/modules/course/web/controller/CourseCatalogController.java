package com.alex.unijourneybackend.modules.course.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.course.application.query.degree_course.GetCoursesHandler;
import com.alex.unijourneybackend.modules.course.application.query.degree_course.GetCoursesQuery;
import com.alex.unijourneybackend.modules.course.web.dto.CourseDto;

/**
 * Endpoint pubblico per la consultazione del curriculum di un corso di laurea
 * (es. da parte di un visitatore non ancora registrato che sfoglia il catalogo).
 * Distinto dagli endpoint admin di gestione corsi.
 */
@RestController
@RequestMapping("/api/v1/course")
public class CourseCatalogController {

    private final GetCoursesHandler handler;

    public CourseCatalogController(GetCoursesHandler handler) {
        this.handler = handler;
    }

    @GetMapping("/read/public/{degreeCourseName}")
    public PageResult<CourseDto> getCoursesByDegreeCourse(
        @PathVariable String degreeCourseName,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "100") int size
    ) {
        var query = new GetCoursesQuery(new PageQuery(page, size));
        return handler.handle(query, degreeCourseName);
    }


}