package com.alex.unijourneybackend.common.infrastructure.exception;

public class GitHubEmailFetchException extends RuntimeException {

    public GitHubEmailFetchException(String message) {
        super(message);
    }

    public GitHubEmailFetchException(String message, Throwable cause) {
        super(message, cause);
    }


}
