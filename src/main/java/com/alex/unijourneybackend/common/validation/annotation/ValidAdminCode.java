package com.alex.unijourneybackend.common.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.alex.unijourneybackend.common.validation.validator.AdminCodeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = AdminCodeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAdminCode {
    String message() default "Invalid admin code, expected format ADMXXXXXXXX";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
