package com.alex.unijourneybackend.common.infrastructure.exception;


import java.io.IOException;



public class EventSerializationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // instance variables
    private final String message;

    // Constructors
    public EventSerializationException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    // Constructors
    public EventSerializationException(String message, IOException e) {
        super(message, e);
        this.message = message;
    }

    // getter
    public String getBaseMessage() {
        return message;
    }


}
