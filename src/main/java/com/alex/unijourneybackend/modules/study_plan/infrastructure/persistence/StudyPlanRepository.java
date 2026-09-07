package com.alex.unijourneybackend.modules.study_plan.infrastructure.persistence;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.modules.study_plan.domain.model.StudyPlan;
import com.alex.unijourneybackend.modules.study_plan.domain.valueobject.StudyPlanId;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;


public interface StudyPlanRepository
    extends JpaRepository<StudyPlan, StudyPlanId> {

    @Override
    @NonNull Page<StudyPlan> findAll(@NonNull Pageable pageable);


    @Override
    @NonNull Optional<StudyPlan> findById(@NonNull StudyPlanId id);


    /**
     * Retrieves a study plan by student id.
     * @param studentId the student id
     * @return Optional containing the study plan if found
     */
    @Query("SELECT sp FROM StudyPlan sp WHERE sp.student.id = :studentId")
    Optional<StudyPlan> findByStudentId(@Param("studentId") UserId studentId);


    /**
     * Checks if a study plan exists for the given student id.
     * @param studentId the student id
     * @return true if exists
     */
    boolean existsByStudent_Id(UserId studentId);


}
