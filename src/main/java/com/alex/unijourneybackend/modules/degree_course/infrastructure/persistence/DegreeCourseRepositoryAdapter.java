package com.alex.unijourneybackend.modules.degree_course.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.degree_course.domain.model.DegreeCourse;
import com.alex.unijourneybackend.modules.degree_course.domain.valueobject.DegreeCourseId;
import com.alex.unijourneybackend.modules.degree_course.port.DegreeCourseRepositoryPort;

@Service
public class DegreeCourseRepositoryAdapter implements DegreeCourseRepositoryPort {

    private final DegreeCourseRepository repository;

    public DegreeCourseRepositoryAdapter(DegreeCourseRepository repository) {
        this.repository = repository;
    }

    @Override
    @SuppressWarnings("null")
    public PageResult<DegreeCourse> findAll(PageQuery query) {
        Page<DegreeCourse> page = repository.findAll(
            PageRequest.of(query.page(), query.size())
        );
        return new PageResult<>(page.getContent(), page.getTotalElements());
    }

    @Override
    @SuppressWarnings("null")
    public Optional<DegreeCourse> findById(DegreeCourseId id) {
        return repository.findById(id);
    }

    @Override
    @SuppressWarnings("null")
    public Optional<DegreeCourse> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    @SuppressWarnings("null")
    public DegreeCourse save(DegreeCourse degreeCourse) {
        return repository.save(degreeCourse);
    }

    @Override
    @SuppressWarnings("null")
    public void delete(DegreeCourse degreeCourse) {
        repository.delete(degreeCourse);
    }

    @Override
    @SuppressWarnings("null")
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }


}
