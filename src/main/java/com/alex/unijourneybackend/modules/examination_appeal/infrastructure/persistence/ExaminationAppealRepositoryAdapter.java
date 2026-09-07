package com.alex.unijourneybackend.modules.examination_appeal.infrastructure.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.course.domain.valueobject.CourseId;
import com.alex.unijourneybackend.modules.examination_appeal.domain.model.ExaminationAppeal;
import com.alex.unijourneybackend.modules.examination_appeal.port.ExaminationAppealRepositoryPort;


@Service
public class ExaminationAppealRepositoryAdapter implements ExaminationAppealRepositoryPort {

    private final ExaminationAppealRepository repository;

    public ExaminationAppealRepositoryAdapter(ExaminationAppealRepository repository) {
        this.repository = repository;
    }


    @Override
    @SuppressWarnings("null")
    public PageResult<ExaminationAppeal> findAll(PageQuery query) {
        Page<ExaminationAppeal> page = repository.findAll(
            PageRequest.of(query.page(), query.size())
        );
        return new PageResult<>(page.getContent(), page.getTotalElements());
    }


    @Override
    @SuppressWarnings("null")
    public Optional<ExaminationAppeal> findById(Long id) {
        return repository.findById(id);
    }


    @Override
    @SuppressWarnings("null")
    public Optional<ExaminationAppeal> findByCourseIdAndDate(CourseId courseId, LocalDate date) {
        return repository.findByCourseIdAndDate(courseId, date);
    }


    @Override
    @SuppressWarnings("null")
    public List<ExaminationAppeal> findByCourseIds(List<UUID> courseIds) {
        return repository.findByCourse_Id_IdIn(courseIds);
    }


    @Override
    @SuppressWarnings("null")
    public List<ExaminationAppeal> findByDateBefore(LocalDate date) {
        return repository.findByDateBefore(date);
    }


    @Override
    @SuppressWarnings("null")
    public ExaminationAppeal save(ExaminationAppeal appeal) {
        return repository.save(appeal);
    }

    @Override
    @SuppressWarnings("null")
    public void delete(ExaminationAppeal appeal) {
        repository.delete(appeal);
    }


}