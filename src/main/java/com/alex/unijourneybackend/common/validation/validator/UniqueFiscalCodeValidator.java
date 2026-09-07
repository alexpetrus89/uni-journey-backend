package com.alex.unijourneybackend.common.validation.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alex.unijourneybackend.common.domain.exception.DataAccessServiceException;
import com.alex.unijourneybackend.common.validation.annotation.UniqueFiscalCode;
import com.alex.unijourneybackend.modules.user.port.in.UserAvailabilityService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueFiscalCodeValidator implements ConstraintValidator<UniqueFiscalCode, String> {

    private static final Logger logger = LoggerFactory.getLogger(UniqueFiscalCodeValidator.class);

    private final UserAvailabilityService availabilityService;

    public UniqueFiscalCodeValidator(UserAvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }


    @Override
    public boolean isValid(String fiscalCode, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(fiscalCode))
            return false;
        try {
            if(availabilityService.isFiscalCodeTaken(fiscalCode)) {
                reject(context, "Error with fiscal code", "fiscalCodeAlreadyTaken");
                return false;
            }
            return true;
        } catch (DataAccessServiceException e) {
            logger.error("Error checking fiscal code availability", e);
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
