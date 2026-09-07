package com.alex.unijourneybackend.modules.degree_course.port;

import java.util.Optional;

import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.degree_course.domain.model.DegreeCourse;
import com.alex.unijourneybackend.modules.degree_course.domain.valueobject.DegreeCourseId;

public interface DegreeCourseRepositoryPort {


    /**
     * Retrieves a page of degree courses.
     * @param query the pagination query
     * @return a page of degree courses
     */
    @NonNull PageResult<DegreeCourse> findAll(@NonNull PageQuery query);

    /**
     * Retrieves a degree course by id.
     * @param id the degree course id
     * @return Optional containing the degree course if found
     */
    @NonNull Optional<DegreeCourse> findById(@NonNull DegreeCourseId id);

    /**
     * Retrieves a degree course by name.
     * @param name the degree course name
     * @return Optional containing the degree course if found
     */
    @NonNull Optional<DegreeCourse> findByName(@NonNull String name);

    /**
     * Saves a degree course.
     * @param degreeCourse the degree course to save
     * @return the saved degree course
     */
    @NonNull DegreeCourse save(@NonNull DegreeCourse degreeCourse);

    /**
     * Deletes a degree course.
     * @param degreeCourse the degree course to delete
     */
    void delete(@NonNull DegreeCourse degreeCourse);

    /**
     * Checks if a degree course exists by name.
     * @param name the degree course name
     * @return true if exists
     */
    boolean existsByName(@NonNull String name);


}