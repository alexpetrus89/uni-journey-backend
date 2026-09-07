package com.alex.unijourneybackend.modules.user.application.command.support;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.alex.unijourneybackend.modules.user.web.dto.response.DeletionResponse;

@Component
public class DeletionResponseFactory {

    private static final String DELETION_SUCCESS_MESSAGE = "Deletion successful";

    /**
     * Creates a user deletion response.
     *
     * @param user the user who deleted
     * @param referenceCode the reference code for the deletion
     * @return the user registration response
     */
    public DeletionResponse create(User user, String referenceCode) {
        return new DeletionResponse(
            user.getId().toString(),
            user.getUsername(),
            user.getRole().name(),
            DELETION_SUCCESS_MESSAGE,
            referenceCode
        );
    }


}
