package com.alex.unijourneybackend.modules.user.application.bus;

public interface CommandHandler<C extends Command<R>, R> {

    /**
     * Handles the given command and returns the result.
     *
     * @param command the command to handle
     * @param R the response
     * @return the result of handling the command
     */
    R handle(C command);
}
