package com.alex.unijourneybackend.modules.degree_course.infrastructure.persistence;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.modules.degree_course.domain.model.DegreeCourse;
import com.alex.unijourneybackend.modules.degree_course.domain.valueobject.DegreeCourseId;


public interface DegreeCourseRepository
    extends JpaRepository<DegreeCourse, DegreeCourseId> {


    /**
     * Retrieves all degree courses from the repository.
     * @param pageable the pagination information
     * @return a page of degree courses
     */
    @Override
    @NonNull
    Page<DegreeCourse> findAll(@NonNull Pageable pageable);


    /**
     * Retrieves a degree course from the repository by its ID.
     * @param id the ID of the degree course to retrieve
     * @return the degree course if found, null otherwise
     */
    @Override
    @NonNull
    Optional<DegreeCourse> findById(@NonNull DegreeCourseId id);


    /**
     * Retrieves a degree course from the repository by its name.
     * @param name the name of the degree course to retrieve
     * @return the degree course if found, null otherwise
     */
    @Query("SELECT s FROM DegreeCourse s WHERE s.name = ?1")
    Optional<DegreeCourse> findByName(String name);


    /**
     * Checks if a degree course exists by its name.
     * @param name the name of the degree course
     * @return boolean
     */
    boolean existsByName(String name);


}
