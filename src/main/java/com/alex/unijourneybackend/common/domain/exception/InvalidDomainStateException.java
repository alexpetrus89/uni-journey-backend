package com.alex.unijourneybackend.common.domain.exception;



public class InvalidDomainStateException extends RuntimeException {

    public InvalidDomainStateException(String message) {
        super(message);
    }

    public InvalidDomainStateException(String message, Throwable cause) {
        super(message, cause);
    }


}
