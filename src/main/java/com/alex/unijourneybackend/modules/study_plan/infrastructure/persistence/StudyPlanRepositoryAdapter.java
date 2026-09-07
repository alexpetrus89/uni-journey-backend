package com.alex.unijourneybackend.modules.study_plan.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.study_plan.domain.model.StudyPlan;
import com.alex.unijourneybackend.modules.study_plan.domain.valueobject.StudyPlanId;
import com.alex.unijourneybackend.modules.study_plan.port.StudyPlanRepositoryPort;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;

@Service
public class StudyPlanRepositoryAdapter implements StudyPlanRepositoryPort {

    private final StudyPlanRepository repository;

    public StudyPlanRepositoryAdapter(StudyPlanRepository repository) {
        this.repository = repository;
    }

    @Override
    @SuppressWarnings("null")
    public PageResult<StudyPlan> findAll(PageQuery query) {
        Page<StudyPlan> page = repository.findAll(
            PageRequest.of(query.page(), query.size())
        );
        return new PageResult<>(page.getContent(), page.getTotalElements());
    }


    @Override
    @SuppressWarnings("null")
    public Optional<StudyPlan> findById(StudyPlanId id) {
        return repository.findById(id);
    }

    @Override
    @SuppressWarnings("null")
    public Optional<StudyPlan> findByStudentId(UserId studentId) {
        return repository.findByStudentId(studentId);
    }


    @Override
    @SuppressWarnings("null")
    public StudyPlan save(StudyPlan studyPlan) {
        return repository.save(studyPlan);
    }


    @Override
    @SuppressWarnings("null")
    public void delete(StudyPlan studyPlan) {
        repository.delete(studyPlan);
    }


    @Override
    @SuppressWarnings("null")
    public boolean existsByStudentId(UserId studentId) {
        return repository.existsByStudent_Id(studentId);
    }


}
