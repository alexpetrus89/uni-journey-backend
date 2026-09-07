package com.alex.unijourneybackend.modules.password.domain.policy;

public class PasswordValidationResult {

    private final boolean valid;
    private final String message;

    private PasswordValidationResult(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    public static PasswordValidationResult valid() {
        return new PasswordValidationResult(true, null);
    }

    public static PasswordValidationResult invalid(String message) {
        return new PasswordValidationResult(false, message);
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }


}
