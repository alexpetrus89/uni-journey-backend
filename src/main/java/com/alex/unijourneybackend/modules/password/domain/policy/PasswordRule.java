package com.alex.unijourneybackend.modules.password.domain.policy;


public interface PasswordRule {

    /**
     * Validate the password
     * @param password
     */
    PasswordValidationResult validate(String password);

    /**
     * Get the priority of the rule
     */
    int priority();

}
