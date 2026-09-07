package com.alex.unijourneybackend.common.validation.validator;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.common.validation.annotation.ValidAdminCode;
import com.alex.unijourneybackend.modules.admin.domain.valueobject.AdminCode;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class AdminCodeValidator implements ConstraintValidator<ValidAdminCode, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return AdminCode.isValid(value);
    }


}
