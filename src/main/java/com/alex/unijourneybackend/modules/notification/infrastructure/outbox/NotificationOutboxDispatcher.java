package com.alex.unijourneybackend.modules.notification.infrastructure.outbox;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class NotificationOutboxDispatcher {

    private final Map<String, NotificationOutboxHandler<?>> handlers;

    public NotificationOutboxDispatcher(List<NotificationOutboxHandler<?>> handlers) {
        this.handlers = handlers
            .stream()
            .collect(Collectors.toUnmodifiableMap(
                h -> h.getType(),
                h -> h,
                (a, b) -> { throw new IllegalStateException("Handler duplicated for type: " + a.getType()); }
            ));
    }

    public void dispatch(String type, String payload, String eventKey) {
        NotificationOutboxHandler<?> handler = handlers.get(type);
        if (handler == null) throw new IllegalStateException("No handler registered for type: " + type);
        handler.handle(payload, eventKey);
    }


}
