package com.alex.unijourneybackend.modules.notification.infrastructure.outbox.kafka;


import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.modules.notification.infrastructure.outbox.NotificationOutbox;
import com.alex.unijourneybackend.modules.notification.port.out.NotificationOutboxRepositoryPort;

@Component
public class NotificationOutboxTransactionalOps {

    private static final int MAX_RETRIES = 5;

    private final NotificationOutboxRepositoryPort port;

    public NotificationOutboxTransactionalOps(NotificationOutboxRepositoryPort port) {
        this.port = port;
    }

    @Transactional
    public List<NotificationOutbox> lockBatch() {
        List<NotificationOutbox> events = port.lockBatch(50);
        events.forEach(event -> Objects.requireNonNull(event, "outbox event must not be null").markProcessing());
        return events; // commit rilascia subito il lock riga, status resta PROCESSING
    }

    @Transactional
    public void markSent(UUID id) {
        Objects.requireNonNull(id, "id must not be null");
        port.findById(id).ifPresent(e -> { e.markSent(); port.save(e); });
    }

    @Transactional
    public void markFailedOrRetry(UUID id, String error) {
        Objects.requireNonNull(id, "id must not be null");
        port.findById(id).ifPresent(e -> {
            if (e.hasExceededRetryLimit(MAX_RETRIES)) e.markFailed(error);
            else e.scheduleRetry(error);
            port.save(e);
        });
    }
}
