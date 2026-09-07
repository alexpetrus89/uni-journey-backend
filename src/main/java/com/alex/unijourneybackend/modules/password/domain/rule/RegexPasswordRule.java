package com.alex.unijourneybackend.modules.password.domain.rule;

import java.util.regex.Pattern;

import com.alex.unijourneybackend.modules.password.domain.policy.PasswordRule;
import com.alex.unijourneybackend.modules.password.domain.policy.PasswordValidationResult;

public class RegexPasswordRule implements PasswordRule {

    private final Pattern pattern;
    private final String message;
    private final int priority;

    public RegexPasswordRule(String regex, String message, int priority) {
        this.pattern = Pattern.compile(regex);
        this.message = message;
        this.priority = priority;
    }

    @Override
    public int priority() { return priority; }

    @Override
    public PasswordValidationResult validate(String password) {

        if (!pattern.matcher(password).matches())
            return PasswordValidationResult.invalid(message);

        return PasswordValidationResult.valid();
    }


}