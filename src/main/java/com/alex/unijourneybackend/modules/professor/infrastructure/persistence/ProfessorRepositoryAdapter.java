package com.alex.unijourneybackend.modules.professor.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.professor.domain.model.Professor;
import com.alex.unijourneybackend.modules.professor.domain.valueobject.ProfessorCode;
import com.alex.unijourneybackend.modules.professor.port.ProfessorRepositoryPort;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;

@Component
public class ProfessorRepositoryAdapter implements ProfessorRepositoryPort {

    private final ProfessorRepository repository;

    public ProfessorRepositoryAdapter(ProfessorRepository repository) {
        this.repository = repository;
    }

    @Override
    @SuppressWarnings("null")
    public PageResult<Professor> findAll(PageQuery query) {
        PageRequest pageRequest = PageRequest.of(query.page(), query.size());

        Page<Professor> page = repository.findAll(pageRequest);

        return new PageResult<>(page.getContent(), page.getTotalElements());
    }

    @Override
    @SuppressWarnings("null")
    public Optional<Professor> findById(UserId id) {
        return repository.findById(id);
    }

    @Override
    @SuppressWarnings("null")
    public Professor create(Professor professor) {
        return repository.save(professor);
    }

    @Override
    @SuppressWarnings("null")
    public void delete(Professor professor) {
        repository.delete(professor);
    }

    @Override
    @SuppressWarnings("null")
    public boolean existsByProfessorCode(ProfessorCode code) {
        return repository.existsByProfessorCode(code);
    }


}

