package com.alex.unijourneybackend.modules.examination_appeal.port;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.course.domain.valueobject.CourseId;
import com.alex.unijourneybackend.modules.examination_appeal.domain.model.ExaminationAppeal;

public interface ExaminationAppealRepositoryPort {


    /**
     * Retrieves a page of examination appeals.
     * @param query the pagination query
     * @return a page of examination appeals
     */
    @NonNull
    PageResult<ExaminationAppeal> findAll(@NonNull PageQuery query);


    /**
     * Retrieves an examination appeal by id.
     * @param id the examination appeal id
     * @return Optional containing the examination appeal if found
     */
    @NonNull
    Optional<ExaminationAppeal> findById(@NonNull Long id);

    /**
     * Retrieves an examination appeal by course id and date.
     * @param courseId the course id
     * @param date     the date of the appeal
     * @return Optional containing the examination appeal if found
     */
    @NonNull Optional<ExaminationAppeal> findByCourseIdAndDate(
        @NonNull CourseId courseId,
        @NonNull LocalDate date
    );


    /**
     * Retrieves all examination appeals for the given course ids.
     * @param courseIds list of course ids
     * @return list of examination appeals
     */
    @NonNull
    List<ExaminationAppeal> findByCourseIds(@NonNull List<UUID> courseIds);


    /**
     * Retrieves all examination appeals with date before the given date.
     * @param date the reference date
     * @return list of examination appeals
     */
    @NonNull
    List<ExaminationAppeal> findByDateBefore(@NonNull LocalDate date);


    /**
     * Saves an examination appeal.
     * @param appeal the examination appeal to save
     * @return the saved examination appeal
     */
    @NonNull
    ExaminationAppeal save(@NonNull ExaminationAppeal appeal);


    /**
     * Deletes an examination appeal.
     * @param appeal the examination appeal to delete
     */
    void delete(@NonNull ExaminationAppeal appeal);


}
