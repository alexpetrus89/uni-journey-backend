package com.alex.unijourneybackend.common.domain.exception;


public class PasswordBlacklistInitializationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PasswordBlacklistInitializationException(String message) {
        super(message);
    }

    public PasswordBlacklistInitializationException(String message, Throwable cause) {
        super(message, cause);
    }


}
