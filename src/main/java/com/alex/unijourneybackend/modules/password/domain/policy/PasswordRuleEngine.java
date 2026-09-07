package com.alex.unijourneybackend.modules.password.domain.policy;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PasswordRuleEngine {

    private final List<PasswordRule> rules;

    public PasswordRuleEngine(List<PasswordRule> rules) {
        this.rules = rules
            .stream()
            .sorted(Comparator.comparingInt(rule -> rule.priority()))
            .toList();
    }

    public PasswordValidationResult validate(String password) {

        for (PasswordRule rule : rules) {
            PasswordValidationResult result = rule.validate(password);
            if (!result.isValid()) return result;
        }

        return PasswordValidationResult.valid();
    }


}
