package com.alex.unijourneybackend.modules.study_plan.application.support;

import java.time.Instant;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.dao.DataAccessException;

import com.alex.unijourneybackend.common.domain.exception.DataAccessServiceException;
import com.alex.unijourneybackend.infrastructure.events.DomainEventPublisher;
import com.alex.unijourneybackend.modules.notification.domain.event.StudyPlanUpdatedEvent;
import com.alex.unijourneybackend.modules.student.domain.model.Student;

import jakarta.persistence.PersistenceException;

public abstract class AbstractUpdatedApplicationService {

    private final DomainEventPublisher publisher;

    protected AbstractUpdatedApplicationService(DomainEventPublisher publisher) {
        this.publisher = publisher;
    }


    /**
     * Publishes a study plan updated event.
     *
     * @param user the user whose study plan was updated
     */
    protected void publishStudyPlanUpdatedEvent(Student student, String courseRemoved, String courseAdded) {

        StudyPlanUpdatedEvent event = new StudyPlanUpdatedEvent(
            student.getUsername(),
            student.getId().toString(),
            courseRemoved,
            courseAdded,
            "Study plan updated successfully",
            "STUDY_PLAN_UPDATED",
            Instant.now()
        );

        publisher.publish((List.of(event)));
    }


    /**
     * Handles data access operations with proper exception handling.
     *
     * @param <T> the type of the result
     * @param message the error message
     * @param action the data access action
     * @return the result of the action
     */
    protected <T> T withDataAccessHandling(String message, Supplier<T> action) {
        try {
            return action.get();
        } catch (DataAccessException | PersistenceException exception) {
            throw new DataAccessServiceException(message, exception);
        }
    }

}



