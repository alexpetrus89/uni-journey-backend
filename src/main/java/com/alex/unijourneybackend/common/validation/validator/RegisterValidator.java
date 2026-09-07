package com.alex.unijourneybackend.common.validation.validator;



import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.common.validation.annotation.ValidRegister;
import com.alex.unijourneybackend.modules.student.domain.valueobject.Register;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class RegisterValidator implements ConstraintValidator<ValidRegister, String> {

    @Override
    public boolean isValid(String register, ConstraintValidatorContext context) {
        return Register.isValid(register);
    }


}
