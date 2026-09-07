package com.alex.unijourneybackend.modules.notification.domain.event;

import java.time.Instant;
import java.util.List;

import com.alex.unijourneybackend.common.events.domain.model.DomainEvent;
import com.alex.unijourneybackend.modules.user.domain.valueobject.RoleType;

public record FacultyNewsEvent(
    String title,
    String payload,
    String type,
    List<RoleType> targetRoles,       // es. [STUDENT] oppure [STUDENT, PROFESSOR]
    String degreeCourseName,          // nullable: null = tutti gli utenti con quei ruoli
    Instant occurredAt
) implements DomainEvent {

    @Override
    public String getType() { return type; }

    @Override
    public String getPayload() { return payload; }

    @Override
    public Instant occurredAt() { return occurredAt; }


}
