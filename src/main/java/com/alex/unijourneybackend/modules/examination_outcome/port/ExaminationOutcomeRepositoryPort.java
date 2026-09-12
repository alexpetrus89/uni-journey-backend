package com.alex.unijourneybackend.modules.examination_outcome.port;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.modules.examination_outcome.domain.model.ExaminationOutcome;

/**
 * Scoperta solo sulle operazioni richieste dai casi d'uso lato studente
 * attualmente implementati (lettura/cancellazione). Se in futuro serviranno
 * handler lato professore (creazione outcome, cleanup schedulato) la porta
 * va estesa di conseguenza.
 */
public interface ExaminationOutcomeRepositoryPort {

    /**
     * Retrieves an examination outcome by id.
     * @param id the outcome id
     * @return Optional containing the outcome if found
     */
    @NonNull Optional<ExaminationOutcome> findById(@NonNull Long id);

    /**
     * Retrieves all examination outcomes for the given student register.
     * @param register the student register
     * @return list of outcomes
     */
    @NonNull List<ExaminationOutcome> findByRegister(@NonNull String register);

    /**
     * Deletes an examination outcome.
     * @param outcome the outcome to delete
     */
    void delete(@NonNull ExaminationOutcome outcome);

    /**
     * Deletes all examination outcomes for the given student register.
     * @param register the student register
     */
    void deleteByRegister(@NonNull String register);


}