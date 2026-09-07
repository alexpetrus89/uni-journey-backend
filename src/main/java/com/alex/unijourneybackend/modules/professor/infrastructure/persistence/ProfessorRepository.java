package com.alex.unijourneybackend.modules.professor.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.modules.professor.domain.model.Professor;
import com.alex.unijourneybackend.modules.professor.domain.valueobject.ProfessorCode;
import com.alex.unijourneybackend.modules.user.domain.valueobject.FiscalCode;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;


public interface ProfessorRepository
    extends JpaRepository<Professor, UserId> {


    /**
     * Retrieves a page of professors.
     * @param pageable the pagination information
     * @return a page of professors
     */
    @Override
    @NonNull
    Page<Professor> findAll(@NonNull Pageable pageable);


    /**
     * Retrieves a professor by the user id
     * @param id the user id of the professor to retrieve
     * @return Optional<Professor> with the professor if found, or an empty
     *        Optional if no professor is found
     */
    @Override
    @NonNull
    Optional<Professor> findById(@NonNull UserId id);

    /**
     * Retrieves a professor by professor code
     * @param code the professor code of the professor to retrieve
     * @return Optional<Professor> with the professor if found, or an empty
     *        Optional if no professor is found
     * @see ProfessorCode
     */
    Optional<Professor> findByProfessorCode(ProfessorCode code);


    /**
     * Retrieves a professor by fiscal code
     * @param code the fiscal code of the professor to retrieve
     * @return Optional<Professor> with the professor if found, or an empty
     *        Optional if no professor is found
     * @see FiscalCode
     */
    Optional<Professor> findByFiscalCode(FiscalCode code);


    /**
     * Retrieves a professor by name
     * @param name the name of the professor to retrieve
     * @return a list of professor with same fullname
     */
    List<Professor> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(
        String firstName,
        String lastName
    );


    /**
     * Checks if a professor exists by professor code
     * @param code the professor code of the professor
     * @return true if the professor exists, false otherwise
     * @see ProfessorCode
     */
    boolean existsByProfessorCode(ProfessorCode code);


    /**
     * Checks if a professor exists by fiscal code
     * @param code the fiscal code of the professor
     * @return true if the professor exists, false otherwise
     * @see FiscalCode
     */
    boolean existsByFiscalCode(FiscalCode code);


    /**
     * Deletes a professor by professor code
     * @param code the professor code of the professor to delete
     * @see ProfessorCode
     */
    @Modifying
    void deleteByProfessorCode(ProfessorCode code);


}
