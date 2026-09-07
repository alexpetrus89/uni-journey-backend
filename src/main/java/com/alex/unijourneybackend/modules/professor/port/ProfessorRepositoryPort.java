package com.alex.unijourneybackend.modules.professor.port;

import java.util.Optional;

import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.professor.domain.model.Professor;
import com.alex.unijourneybackend.modules.professor.domain.valueobject.ProfessorCode;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;


public interface ProfessorRepositoryPort {

    /**
     * Retrieves a page of professors.
     * @param query The pagination information.
     * @return A page of professors.
     */
    @NonNull
    PageResult<Professor> findAll(@NonNull PageQuery query);

    /**
     * Retrieves a professor by their ID.
     * @param id The professor ID.
     * @return The professor.
     */
    @NonNull
    Optional<Professor> findById(@NonNull UserId id);

    /**
     * Creates a new professor.
     * @param admin The professor to create.
     * @return The created professor.
     */
    @NonNull
    Professor create(@NonNull Professor professor);

    /**
     * Deletes an professor.
     * @param professor The professor to delete.
     */
    void delete(@NonNull Professor professor);

    /**
     * Checks if professor with the given professor code exists.
     * @param code The professor code to check.
     * @return true if a professor with the given professor code exists, false otherwise.
    */
    boolean existsByProfessorCode(@NonNull ProfessorCode code);


}

