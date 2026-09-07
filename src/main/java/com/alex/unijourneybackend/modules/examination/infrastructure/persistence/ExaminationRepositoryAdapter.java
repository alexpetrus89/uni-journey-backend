package com.alex.unijourneybackend.modules.examination.infrastructure.persistence;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.examination.domain.model.Examination;
import com.alex.unijourneybackend.modules.examination.domain.valueobject.ExaminationId;
import com.alex.unijourneybackend.modules.examination.port.ExaminationRepositoryPort;

@Service
public class ExaminationRepositoryAdapter implements ExaminationRepositoryPort {

    private final ExaminationRepository repository;

    public ExaminationRepositoryAdapter(ExaminationRepository repository) {
        this.repository = repository;
    }

    @Override
    @SuppressWarnings("null")
    public PageResult<Examination> findAll(PageQuery query) {
        Page<Examination> page = repository.findAll(
            PageRequest.of(query.page(), query.size())
        );
        return new PageResult<>(page.getContent(), page.getTotalElements());
    }

    @Override
    @SuppressWarnings("null")
    public Optional<Examination> findById(ExaminationId id) {
        return repository.findById(id);
    }

    @Override
    @SuppressWarnings("null")
    public List<Examination> findByRegister(String register) {
        return repository.findByRegister(register);
    }

    @Override
    @SuppressWarnings("null")
    public List<Examination> findByCourseId(UUID courseId) {
        return repository.findByCourse_Id_Id(courseId);
    }

    @Override
    @SuppressWarnings("null")
    public Examination save(Examination examination) {
        return repository.save(examination);
    }

    @Override
    @SuppressWarnings("null")
    public void delete(Examination examination) {
        repository.delete(examination);
    }


}
