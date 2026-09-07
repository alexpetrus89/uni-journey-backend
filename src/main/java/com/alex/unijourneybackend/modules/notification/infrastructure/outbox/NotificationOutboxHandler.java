package com.alex.unijourneybackend.modules.notification.infrastructure.outbox;

import com.alex.unijourneybackend.common.events.domain.model.DomainEvent;

public interface NotificationOutboxHandler<E extends DomainEvent> {

    /**
     * Returns the type of the event this handler can process.
     * @return the event type
     */
    String getType();

    /**
     * Handles the event.
     * @param payload the event payload
     */
    void handle(String payload, String eventKey);


}
