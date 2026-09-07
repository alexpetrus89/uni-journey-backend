package com.alex.unijourneybackend.modules.course.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.modules.course.domain.model.Course;
import com.alex.unijourneybackend.modules.course.domain.valueobject.CourseId;
import com.alex.unijourneybackend.modules.professor.domain.valueobject.ProfessorCode;
import com.alex.unijourneybackend.modules.study.domain.enums.CourseType;


public interface CourseRepository extends JpaRepository<Course, CourseId> {


    /**
     * Retrieves all courses from the repository.
     * @param pageable the pagination information
     * @return a page of courses
     */
    @Override
    @NonNull
    Page<Course> findAll(@NonNull Pageable pageable);


    /**
     * Retrieves a course from the repository by its ID.
     * @param id the ID of the course to retrieve
     * @return the course if found, null otherwise
     */
    @Override
    @NonNull
    Optional<Course> findById(@NonNull CourseId id);


    /**
     * Retrieves a course from the repository by its name and degree course.
     * @param courseName the name of the course
     * @param degreeCourseName the name of degree course
     * @return an Optional containing the course if found, empty otherwise
     */
    @Query("SELECT c FROM Course c WHERE c.name = ?1 AND c.degreeCourse.name = ?2")
    Optional<Course> findByNameAndDegreeCourseName(
        @Param("courseName") String courseName,
        @Param("degreeCourseName") String degreeCourseName
    );


    /**
     * Retrieves a course from the repository by its type.
     * @param pageable the pagination information
     * @param type the type of the course to retrieve
     * @return a page of courses if found, null otherwise
     * @see CourseType
     */
    @Query("SELECT c FROM Course c WHERE c.type = :type")
    Page<Course> findByType(Pageable pageable, @Param("type") CourseType type);


    /**
     * Retrieves a list of courses associated with a specific degree course.
     * @param pageable the pagination information
     * @param name the name of the degree course whose courses
     *                   are to be retrieved
     * @return a page of courses if found, null otherwise
     */
    @Query("SELECT c FROM Course c JOIN c.degreeCourse dc WHERE dc.name = ?1")
    Page<Course> findByDegreeCourseName(Pageable pageable, String name);


    /**
     * Retrieves a list of courses associated with a specific professor.
     * @param pageable the pagination information
     * @param professorCode the professor code of the professor whose courses
     *                   are to be retrieved
     * @return a page of courses if found, null otherwise
     * @see ProfessorCode
     */
    @Query("SELECT c FROM Course c JOIN c.professor p WHERE p.professorCode = ?1")
    Page<Course> findByProfessor(Pageable pageable, ProfessorCode code);


    /**
     * Checks if a course exists by its name.
     * @param name the name of the course
     * @return boolean
     */
    boolean existsByName(String name);


    /**
     * Checks if a course exists by its name and degree course
     * @param courseName
     * @param degreeCourseName
     * @return boolean
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END "
    + "FROM Course c JOIN c.degreeCourse dc "
    + "WHERE c.name = :courseName AND dc.name = :degreeCourseName")
    boolean existsByNameAndDegreeCourseName(@Param("courseName") String courseName, @Param("degreeCourseName") String degreeCourseName);


}