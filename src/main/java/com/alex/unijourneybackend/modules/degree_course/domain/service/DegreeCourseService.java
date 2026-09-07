package com.alex.unijourneybackend.modules.degree_course.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.modules.degree_course.port.DegreeCourseRepositoryPort;
import com.alex.unijourneybackend.modules.degree_course.web.dto.DegreeCourseDto;
import com.alex.unijourneybackend.modules.degree_course.web.mapper.DegreeCourseMapper;

@Service
public class DegreeCourseService {

    private final DegreeCourseRepositoryPort port;

    public DegreeCourseService(DegreeCourseRepositoryPort port) {
        this.port = port;
    }

    public List<DegreeCourseDto> getCatalog() {
        return port
            .findAll(PageQuery.unpaged())
            .content()
            .stream()
            .map(DegreeCourseMapper::toDto)
            .sorted(DegreeCourseDto.BY_GRADUATION)
            .toList();
    }


}
