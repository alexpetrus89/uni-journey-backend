package com.alex.unijourneybackend.modules.password.domain.rule;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.password.domain.config.PasswordProperties;
import com.alex.unijourneybackend.modules.password.domain.policy.PasswordRule;
import com.alex.unijourneybackend.modules.password.domain.policy.PasswordValidationResult;

@Component
public class LengthPasswordRule implements PasswordRule {

    private final PasswordProperties properties;

    public LengthPasswordRule(PasswordProperties properties) {
        this.properties = properties;
    }

    @Override
    public int priority() { return 1; }

    @Override
    public PasswordValidationResult validate(String password) {

        if (password.length() < properties.getMinLength())
            return PasswordValidationResult.invalid("Password must be at least " + properties.getMinLength() + " characters");

        return PasswordValidationResult.valid();
    }


}
