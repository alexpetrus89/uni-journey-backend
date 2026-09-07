package com.alex.unijourneybackend.common.domain.exception;


public class DuplicateUsernameException extends RuntimeException {

    public DuplicateUsernameException(String username) {
        super("Username '" + username + "' is already taken by another user");
    }


}

