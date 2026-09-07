package com.alex.unijourneybackend.common.validation.validator;


import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.common.validation.annotation.ValidPassword;
import com.alex.unijourneybackend.modules.password.domain.policy.PasswordRuleEngine;
import com.alex.unijourneybackend.modules.password.domain.policy.PasswordValidationResult;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


@Component
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private final PasswordRuleEngine engine;

    public PasswordValidator(PasswordRuleEngine engine) {
        this.engine = engine;
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) return false;
        PasswordValidationResult result = engine.validate(password);
        if (result.isValid()) return true;
        return setMessage(context, result.getMessage());
    }

    private boolean setMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return false;
    }


}

