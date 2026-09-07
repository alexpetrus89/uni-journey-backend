package com.alex.unijourneybackend.modules.user.application.command.support;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.alex.unijourneybackend.modules.user.web.dto.response.RegistrationResponse;

@Component
public class RegistrationResponseFactory {

    private static final String REGISTRATION_SUCCESS_MESSAGE = "Registration successful";

    /**
     * Creates a user registration response.
     *
     * @param user the user who registered
     * @param referenceCode the reference code for the registration
     * @return the user registration response
     */
    public RegistrationResponse create(User user, String referenceCode) {
        return new RegistrationResponse(
            user.getId().toString(),
            user.getUsername(),
            user.getRole().name(),
            REGISTRATION_SUCCESS_MESSAGE,
            referenceCode
        );
    }


}
