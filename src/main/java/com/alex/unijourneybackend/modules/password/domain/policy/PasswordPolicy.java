package com.alex.unijourneybackend.modules.password.domain.policy;


public class PasswordPolicy {

    private final int minLength;
    private final boolean requireUppercase;
    private final boolean requireLowercase;
    private final boolean requireDigit;
    private final boolean requireSpecial;

    public PasswordPolicy(
        int minLength,
        boolean requireUppercase,
        boolean requireLowercase,
        boolean requireDigit,
        boolean requireSpecial
    ) {
        this.minLength = minLength;
        this.requireUppercase = requireUppercase;
        this.requireLowercase = requireLowercase;
        this.requireDigit = requireDigit;
        this.requireSpecial = requireSpecial;
    }

    public int getMinLength() {
        return minLength;
    }

    public boolean requireUppercase() {
        return requireUppercase;
    }

    public boolean requireLowercase() {
        return requireLowercase;
    }

    public boolean requireDigit() {
        return requireDigit;
    }

    public boolean requireSpecial() {
        return requireSpecial;
    }


}
