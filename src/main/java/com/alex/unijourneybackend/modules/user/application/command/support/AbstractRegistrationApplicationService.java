package com.alex.unijourneybackend.modules.user.application.command.support;

import java.time.Instant;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.dao.DataAccessException;

import com.alex.unijourneybackend.common.domain.exception.DataAccessServiceException;
import com.alex.unijourneybackend.infrastructure.events.DomainEventPublisher;
import com.alex.unijourneybackend.modules.notification.domain.event.UserRegisteredEvent;
import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.alex.unijourneybackend.modules.user.web.dto.response.RegistrationResponse;

import jakarta.persistence.PersistenceException;

public abstract class AbstractRegistrationApplicationService {

    private final DomainEventPublisher publisher;
    private final RegistrationResponseFactory factory;

    protected AbstractRegistrationApplicationService(
        DomainEventPublisher publisher,
        RegistrationResponseFactory factory
    ) {
        this.publisher = publisher;
        this.factory = factory;
    }


    /**
     * Publishes a user registered event.
     *
     * @param user the user who registered
     */
    protected void publishUserRegisteredEvent(User user) {

        UserRegisteredEvent event = new UserRegisteredEvent(
            user.getId().toString(),
            user.getUsername(),
            user.getRole(),
            "User registered successfully",
            "USER_REGISTERED",
            Instant.now()
        );

        publisher.publish((List.of(event)));
    }


    /**
     * Builds a user registration response.
     *
     * @param user the user who registered
     * @param referenceCode the reference code for the registration
     * @return the user registration response
     */
    protected RegistrationResponse buildRegistrationResponse(User user, String referenceCode) {
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
