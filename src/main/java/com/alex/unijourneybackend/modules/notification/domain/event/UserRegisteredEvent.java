package com.alex.unijourneybackend.modules.notification.domain.event;

import java.time.Instant;

import com.alex.unijourneybackend.common.events.domain.model.DomainEvent;
import com.alex.unijourneybackend.modules.user.domain.valueobject.RoleType;

public record UserRegisteredEvent(
    String userId,
    String username,
    RoleType role,
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