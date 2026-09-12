package com.alex.unijourneybackend.modules.examination_outcome.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.modules.examination_outcome.domain.model.ExaminationOutcome;
import com.alex.unijourneybackend.modules.examination_outcome.port.ExaminationOutcomeRepositoryPort;

@Service
public class ExaminationOutcomeRepositoryAdapter implements ExaminationOutcomeRepositoryPort {

    private final ExaminationOutcomeRepository repository;

    public ExaminationOutcomeRepositoryAdapter(ExaminationOutcomeRepository repository) {
        this.repository = repository;
    }

    @Override
    @SuppressWarnings("null")
    public Optional<ExaminationOutcome> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @SuppressWarnings("null")
    public List<ExaminationOutcome> findByRegister(String register) {
        return repository.findByRegister(register);
    }

    @Override
    @SuppressWarnings("null")
    public void delete(ExaminationOutcome outcome) {
        repository.delete(outcome);
    }

    @Override
    @SuppressWarnings("null")
    public void deleteByRegister(String register) {
        repository.deleteByRegister(register);
    }


}
