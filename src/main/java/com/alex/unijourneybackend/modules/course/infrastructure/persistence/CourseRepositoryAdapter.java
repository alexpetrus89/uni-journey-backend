package com.alex.unijourneybackend.modules.course.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.course.domain.model.Course;
import com.alex.unijourneybackend.modules.course.domain.valueobject.CourseId;
import com.alex.unijourneybackend.modules.course.port.CourseRepositoryPort;
import com.alex.unijourneybackend.modules.professor.domain.valueobject.ProfessorCode;
import com.alex.unijourneybackend.modules.study.domain.enums.CourseType;

@Service
public class CourseRepositoryAdapter implements CourseRepositoryPort {

    private final CourseRepository repository;

    public CourseRepositoryAdapter(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    @SuppressWarnings("null")
    public PageResult<Course> findAll(PageQuery query) {
        Page<Course> page = repository.findAll(PageRequest.of(query.page(), query.size()));
        return new PageResult<>(page.getContent(), page.getTotalElements());
    }

    @Override
    @SuppressWarnings("null")
    public Optional<Course> findById(CourseId id) {
        return repository.findById(id);
    }

    @Override
    @SuppressWarnings("null")
    public Optional<Course> findByNameAndDegreeCourseName(String courseName, String degreeCourseName) {
        return repository.findByNameAndDegreeCourseName(courseName, degreeCourseName);
    }

    @Override
    @SuppressWarnings("null")
    public PageResult<Course> findByType(PageQuery query, CourseType type) {
        if (type == null) return new PageResult<>(List.of(), 0);
        PageRequest pageRequest = PageRequest.of(query.page(), query.size());
        Page<Course> page = repository.findByType(pageRequest, type);
        return new PageResult<>(page.getContent(), page.getTotalElements());
    }

    @Override
    @SuppressWarnings("null")
    public PageResult<Course> findByDegreeCourseName(PageQuery query, String name) {
        PageRequest pageRequest = PageRequest.of(query.page(), query.size());
        Page<Course> page = repository.findByDegreeCourseName(pageRequest, name);
        return new PageResult<>(page.getContent(), page.getTotalElements());
    }

    @Override
    @SuppressWarnings("null")
    public PageResult<Course> findByProfessor(ProfessorCode code) {
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE); // Fetch all courses for the professor
        Page<Course> page = repository.findByProfessor(pageRequest, code);
        return new PageResult<>(page.getContent(), page.getTotalElements());
    }

    @Override
    @SuppressWarnings("null")
    public Course save(Course course) {
        return repository.save(course);
    }

    @Override
    @SuppressWarnings("null")
    public void delete(Course course) {
        repository.delete(course);
    }

    @Override
    @SuppressWarnings("null")
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    @SuppressWarnings("null")
    public boolean existsByNameAndDegreeCourseName(
        String courseName,
        String degreeCourseName
    ) {
        return repository.existsByNameAndDegreeCourseName(courseName, degreeCourseName);
    }


}
