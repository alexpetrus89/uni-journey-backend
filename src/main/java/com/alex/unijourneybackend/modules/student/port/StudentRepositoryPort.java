package com.alex.unijourneybackend.modules.student.port;

import java.util.Optional;

import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.student.domain.model.Student;
import com.alex.unijourneybackend.modules.student.domain.valueobject.Register;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;

public interface StudentRepositoryPort {

    /**
     * Retrieves a page of students.
     * @param query The pagination information.
     * @return A page of students.
     */
    @NonNull
    PageResult<Student> findAll(@NonNull PageQuery query);

    /**
     * Retrieves a student by their ID.
     * @param id The student ID.
     * @return The student.
     */
    @NonNull
    Optional<Student> findById(@NonNull UserId id);

    /**
     * Creates a new student.
     * @param student The student to create.
     * @return The created student.
     */
    @NonNull
    Student create(@NonNull Student student);

    /**
     * Deletes a student.
     * @param student The student to delete.
     */
    void delete(@NonNull Student student);

    /**
     * Checks if a student with the given register exists
     * @param register The register to check.
     * @return true if a student with the given register exists, false otherwise.
    */
    boolean existsByRegister(@NonNull Register register);


}