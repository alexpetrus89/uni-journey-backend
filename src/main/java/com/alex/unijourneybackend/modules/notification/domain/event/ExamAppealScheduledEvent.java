package com.alex.unijourneybackend.modules.notification.domain.event;

import java.time.Instant;
import java.time.LocalDateTime;

import com.alex.unijourneybackend.common.events.domain.model.DomainEvent;

public record ExamAppealScheduledEvent(
    String username,
    String payload,
    String type,
    LocalDateTime date,
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
