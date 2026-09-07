package com.alex.unijourneybackend.common.validation.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alex.unijourneybackend.common.domain.exception.DataAccessServiceException;
import com.alex.unijourneybackend.common.validation.annotation.UniqueUsername;
import com.alex.unijourneybackend.modules.user.port.in.UserAvailabilityService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private static final Logger logger = LoggerFactory.getLogger(UniqueUsernameValidator.class);

    private final UserAvailabilityService availabilityService;

    public UniqueUsernameValidator(UserAvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(username))
            return false;

        try {
            if (availabilityService.isUsernameTaken(username)) {
                reject(context, "Username is already taken", "usernameAlreadyTaken");
                return false;
            }
            return true;
        } catch (DataAccessServiceException e) {
            logger.error("Error checking username availability", e);
            return false;
        }
    }

    private void reject(ConstraintValidatorContext context, String message, String fieldName) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
            .addPropertyNode(fieldName)
            .addConstraintViolation();
    }


}

