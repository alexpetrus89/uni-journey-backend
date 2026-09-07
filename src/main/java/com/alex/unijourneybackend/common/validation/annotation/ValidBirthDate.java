package com.alex.unijourneybackend.common.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.alex.unijourneybackend.common.validation.validator.BirthDateValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = BirthDateValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBirthDate {
    String message() default "The date of birth cannot be today or in the future.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
