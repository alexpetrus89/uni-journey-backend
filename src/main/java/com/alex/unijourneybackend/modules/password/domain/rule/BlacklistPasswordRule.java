package com.alex.unijourneybackend.modules.password.domain.rule;


import com.alex.unijourneybackend.modules.password.domain.policy.PasswordRule;
import com.alex.unijourneybackend.modules.password.domain.policy.PasswordValidationResult;
import com.alex.unijourneybackend.modules.password.domain.port.PasswordBlacklist;


public class BlacklistPasswordRule implements PasswordRule {

    private final PasswordBlacklist blacklist;
    public BlacklistPasswordRule(PasswordBlacklist blacklist) {
        this.blacklist = blacklist;
    }

    @Override
    public int priority() { return 3; }

    @Override
    public PasswordValidationResult validate(String password) {

        if (blacklist.contains(password))
            return PasswordValidationResult.invalid("Password is too common or compromised");

        return PasswordValidationResult.valid();
    }


}
