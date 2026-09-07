package com.alex.unijourneybackend.common.domain.exception;



public class DataAccessServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataAccessServiceException(String message) {
        super(message);
    }

    public DataAccessServiceException(String message, Throwable cause) {
        super(message, cause);
    }


}
