package com.alex.unijourneybackend.modules.user.application.bus;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;


@Component
public class CustomCommandBus implements CommandBus {

    private final Map<Class<?>, CommandHandler<?, ?>> handlers;

    public CustomCommandBus(List<CommandHandler<?, ?>> handlerList) {
        this.handlers = handlerList
            .stream()
            .collect(Collectors.toMap(this::resolveCommandType, Function.identity()));
    }


    @SuppressWarnings("unchecked")
    @Override
    public <R> R dispatch(Command<R> command) {
        CommandHandler<Command<R>, R> handler =
            (CommandHandler<Command<R>, R>) handlers.get(command.getClass());

        if (handler == null)
            throw new IllegalStateException("No handler for " + command.getClass());

        return handler.handle(command);
    }


    private Class<?> resolveCommandType(CommandHandler<?, ?> handler) {
        return ResolvableType
            .forClass(handler.getClass())
            .as(CommandHandler.class)
            .getGeneric(0)
            .resolve();
    }


}
