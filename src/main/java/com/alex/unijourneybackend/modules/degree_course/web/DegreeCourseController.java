package com.alex.unijourneybackend.modules.degree_course.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.unijourneybackend.modules.degree_course.domain.service.DegreeCourseService;
import com.alex.unijourneybackend.modules.degree_course.web.dto.DegreeCourseDto;

@RestController
@RequestMapping("/api/v1/degree-course")
public class DegreeCourseController {

    private final DegreeCourseService service;

    public DegreeCourseController(DegreeCourseService service) {
        this.service = service;
    }

    @GetMapping("/catalog")
    public ResponseEntity<List<DegreeCourseDto>> getCatalog() {
        return ResponseEntity.ok(service.getCatalog());
    }


}