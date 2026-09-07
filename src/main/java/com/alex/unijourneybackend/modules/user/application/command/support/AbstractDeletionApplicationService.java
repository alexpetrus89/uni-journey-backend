package com.alex.unijourneybackend.modules.user.application.command.support;

import java.time.Instant;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.dao.DataAccessException;

import com.alex.unijourneybackend.common.domain.exception.DataAccessServiceException;
import com.alex.unijourneybackend.infrastructure.events.DomainEventPublisher;
import com.alex.unijourneybackend.modules.notification.domain.event.UserDeletedEvent;
import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.alex.unijourneybackend.modules.user.web.dto.response.DeletionResponse;

import jakarta.persistence.PersistenceException;

public abstract class AbstractDeletionApplicationService {

    private final DomainEventPublisher publisher;
    private final DeletionResponseFactory factory;

    protected AbstractDeletionApplicationService(
        DomainEventPublisher publisher,
        DeletionResponseFactory factory
    ) {
        this.publisher = publisher;
        this.factory = factory;
    }


    /**
     * Publishes a user deleted event.
     *
     * @param user the user who was deleted
     */
    protected void publishUserDeletedEvent(User user) {

        UserDeletedEvent event = new UserDeletedEvent(
            user.getId().toString(),
            user.getUsername(),
            user.getRole(),
            "User deleted successfully",
            "USER_DELETED",
            Instant.now()
        );

        publisher.publish((List.of(event)));
    }


    /**
     * Builds a user deletion response.
     *
     * @param user the user who deleted
     * @param referenceCode the reference code for the deletion
     * @return the user deletion response
     */
    protected DeletionResponse buildDeletionResponse(User user, String referenceCode) {
        return factory.create(user, referenceCode);
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
