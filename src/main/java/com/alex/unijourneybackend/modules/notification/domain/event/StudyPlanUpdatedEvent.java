package com.alex.unijourneybackend.modules.notification.domain.event;

import java.time.Instant;

import com.alex.unijourneybackend.common.events.domain.model.DomainEvent;

public record StudyPlanUpdatedEvent(
    String username,
    String studentId,
    String courseRemoved,
    String courseAdded,
    String payload,
    String type,
    Instant occurredAt
) implements DomainEvent {

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getPayload() {
        return payload;
    }

    @Override
    public Instant occurredAt() {
        return occurredAt;
    }


}
