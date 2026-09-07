package com.alex.unijourneybackend.modules.examination.infrastructure.persistence;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.modules.examination.domain.model.Examination;
import com.alex.unijourneybackend.modules.examination.domain.valueobject.ExaminationId;


public interface ExaminationRepository
    extends JpaRepository<Examination, ExaminationId>{

    /**
     * Retrieves all examinations.
     * @param pageable the pagination information
     * @return a page of examinations
     */
    @Override
    @NonNull Page<Examination> findAll(@NonNull Pageable pageable);


    /**
     * Retrieves an examination by its ID.
     * @param id the ID of the examination
     * @return an optional containing the examination if found, or empty if not found
     */
    @Override
    @NonNull Optional<Examination> findById(@NonNull ExaminationId id);


    /**
     * Retrieves all examinations for the given student register.
     *
     * @param register the register of the student
     * @return list of examinations associated with the student
     */
    List<Examination> findByRegister(String register);


    /**
     * Retrieves all examinations for the given course ID.
     *
     * @param courseId the ID of the course
     * @return list of examinations associated with the specified course
     */
    List<Examination> findByCourse_Id_Id(UUID courseId);


}