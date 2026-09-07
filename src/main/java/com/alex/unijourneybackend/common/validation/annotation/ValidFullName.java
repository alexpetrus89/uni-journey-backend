package com.alex.unijourneybackend.common.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.alex.unijourneybackend.common.validation.validator.FullNameValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = FullNameValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFullName {
    String message() default "Full name must include first name and last name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

