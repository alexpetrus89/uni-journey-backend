package com.alex.unijourneybackend.common.validation.validator;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.common.validation.annotation.ValidBirthDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class BirthDateValidator implements ConstraintValidator<ValidBirthDate, LocalDate> {

    @Override
    public boolean isValid(LocalDate dob, ConstraintValidatorContext context) {
        if (dob == null) return true;
        // La validazione @NotNull dovrebbe gestire i valori null
        return dob.isBefore(LocalDate.now());
    }


}
