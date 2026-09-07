package com.alex.unijourneybackend.common.validation.validator;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.common.validation.PasswordCarrier;
import com.alex.unijourneybackend.common.validation.annotation.PasswordMatches;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


@Component
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, PasswordCarrier> {

    @Override
    public boolean isValid(PasswordCarrier carrier, ConstraintValidatorContext context) {
        if (carrier == null) return false;
        String password = carrier.getPassword();
        String confirm  = carrier.getConfirm();
        return password != null && confirm != null && password.equals(confirm);
    }


}

