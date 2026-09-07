package com.alex.unijourneybackend.modules.study_plan.port;

import java.util.Optional;

import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.study_plan.domain.model.StudyPlan;
import com.alex.unijourneybackend.modules.study_plan.domain.valueobject.StudyPlanId;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;



public interface StudyPlanRepositoryPort {

    /**
     * Retrieves a page of study plans.
     * @param query the pagination query
     * @return a page of study plans
     */
    @NonNull PageResult<StudyPlan> findAll(@NonNull PageQuery query);

    /**
     * Retrieves a study plan by id.
     * @param id the study plan id
     * @return Optional containing the study plan if found
     */
    @NonNull Optional<StudyPlan> findById(@NonNull StudyPlanId id);


    /**
     * Retrieves a study plan by student id.
     * @param studentId the student id
     * @return Optional containing the study plan if found
     */
    @NonNull Optional<StudyPlan> findByStudentId(@NonNull UserId studentId);


    /**
     * Saves a study plan.
     * @param studyPlan the study plan to save
     * @return the saved study plan
     */
    @NonNull StudyPlan save(@NonNull StudyPlan studyPlan);


    /**
     * Deletes a study plan.
     * @param studyPlan the study plan to delete
     */
    void delete(@NonNull StudyPlan studyPlan);


    /**
     * Checks if a study plan exists for the given student id.
     * @param studentId the student id
     * @return true if exists
     */
    boolean existsByStudentId(@NonNull UserId studentId);

}
