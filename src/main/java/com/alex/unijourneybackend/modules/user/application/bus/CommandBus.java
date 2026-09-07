package com.alex.unijourneybackend.modules.user.application.bus;

public interface CommandBus {

    /**
     * Dispatches the given command and returns the result.
     *
     * @param command the command to dispatch
     * @return the result of dispatching the command
     */
    <R> R dispatch(Command<R> command);
}
