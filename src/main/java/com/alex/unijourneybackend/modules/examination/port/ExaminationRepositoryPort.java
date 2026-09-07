package com.alex.unijourneybackend.modules.examination.port;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.examination.domain.model.Examination;
import com.alex.unijourneybackend.modules.examination.domain.valueobject.ExaminationId;

public interface ExaminationRepositoryPort {

    /**
     * Retrieves a page of examinations.
     * @param query the pagination query
     * @return a page of examinations
     */
    @NonNull PageResult<Examination> findAll(@NonNull PageQuery query);

    /**
     * Retrieves an examination by id.
     * @param id the examination id
     * @return Optional containing the examination if found
     */
    @NonNull Optional<Examination> findById(@NonNull ExaminationId id);

    /**
     * Retrieves all examinations for the given student register.
     * @param register the student register
     * @return list of examinations
     */
    @NonNull List<Examination> findByRegister(@NonNull String register);

    /**
     * Retrieves all examinations for the given course id.
     * @param courseId the course id
     * @return list of examinations
     */
    @NonNull List<Examination> findByCourseId(@NonNull UUID courseId);

    /**
     * Saves an examination.
     * @param examination the examination to save
     * @return the saved examination
     */
    @NonNull Examination save(@NonNull Examination examination);

    /**
     * Deletes an examination.
     * @param examination the examination to delete
     */
    void delete(@NonNull Examination examination);


}
