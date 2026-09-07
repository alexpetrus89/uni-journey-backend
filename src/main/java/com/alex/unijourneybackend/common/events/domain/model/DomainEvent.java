package com.alex.unijourneybackend.common.events.domain.model;

import java.time.Instant;

public interface DomainEvent {

    /**
     * The type of the event
     * @return the type of the event
     */
    String getType();

    /**
     * The payload of the event
     * @return the payload of the event
     */
    String getPayload();

    /**
     * The time the event occurred.
     */
    Instant occurredAt();

}
