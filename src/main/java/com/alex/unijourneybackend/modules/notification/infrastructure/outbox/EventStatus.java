package com.alex.unijourneybackend.modules.notification.infrastructure.outbox;

public enum EventStatus {
    PENDING,
    PROCESSING,
    SENT,
    FAILED
}

