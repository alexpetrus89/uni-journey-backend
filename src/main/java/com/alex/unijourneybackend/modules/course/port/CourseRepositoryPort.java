package com.alex.unijourneybackend.modules.course.port;

import java.util.Optional;

import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.course.domain.model.Course;
import com.alex.unijourneybackend.modules.course.domain.valueobject.CourseId;
import com.alex.unijourneybackend.modules.professor.domain.valueobject.ProfessorCode;
import com.alex.unijourneybackend.modules.study.domain.enums.CourseType;

public interface CourseRepositoryPort {

    /**
     * Retrieves a page of courses.
     * @param query the pagination query
     * @return a page of courses
     */
    @NonNull
    PageResult<Course> findAll(@NonNull PageQuery query);

    /**
     * Retrieves a course by id.
     * @param id the course id
     * @return Optional containing the course if found
     */
    @NonNull
    Optional<Course> findById(@NonNull CourseId id);

    /**
     * Retrieves a course by name and degree course name.
     * @param courseName       the course name
     * @param degreeCourseName the degree course name
     * @return Optional containing the course if found
     */
    @NonNull
    Optional<Course> findByNameAndDegreeCourseName(
        @NonNull String courseName,
        @NonNull String degreeCourseName
    );

    /**
     * Retrieves all courses of a given type.
     * @param type the course type
     * @return page of matching courses
     */
    @NonNull
    PageResult<Course> findByType(@NonNull PageQuery query, @NonNull CourseType type);

    /**
     * Retrieves all courses associated with a degree course.
     * @param name the name of the degree course
     * @return page of matching courses
     */
    @NonNull
    PageResult<Course> findByDegreeCourseName(@NonNull PageQuery query, @NonNull String name);

    /**
     * Retrieves all courses associated with a professor.
     * @param code the professor code
     * @return page of matching courses
     */
    @NonNull
    PageResult<Course> findByProfessor(@NonNull ProfessorCode code);

    /**
     * Saves a course.
     * @param course the course to save
     * @return the saved course
     */
    @NonNull
    Course save(@NonNull Course course);

    /**
     * Deletes a course.
     * @param course the course to delete
     */
    void delete(@NonNull Course course);

    /**
     * Checks if a course exists by name.
     * @param name the course name
     * @return true if exists
     */
    boolean existsByName(@NonNull String name);

    /**
     * Checks if a course exists by name and degree course name.
     * @param courseName       the course name
     * @param degreeCourseName the degree course name
     * @return true if exists
     */
    boolean existsByNameAndDegreeCourseName(
        @NonNull String courseName,
        @NonNull String degreeCourseName
    );


}
