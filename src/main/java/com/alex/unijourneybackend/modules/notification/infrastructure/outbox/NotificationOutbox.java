package com.alex.unijourneybackend.modules.notification.infrastructure.outbox;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "NOTIFICATIONS_EVENT", schema = "communication")
@Access(AccessType.FIELD)
public class NotificationOutbox {

    // =========================
    // Instance Variables
    // =========================
    @Id
    private UUID id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String payload;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventStatus status;

    @Column(nullable = false)
    private int retryCount;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime lastAttemptAt;
    private LocalDateTime nextAttemptAt;

    @Column(name = "event_key", unique = true, nullable = false)
    private String eventKey;

    @Column(columnDefinition = "TEXT")
    private String errorMessage;

    @Version
    private long version;

    protected NotificationOutbox() {}

    /**
     * @param type     tipo semantico dell'evento (es. "EXAM_OUTCOME")
     * @param payload  JSON serializzato
     * @param eventKey chiave deterministica per idempotenza (costruita dal chiamante)
     */
    public static NotificationOutbox create(String type, String payload, String eventKey) {
        NotificationOutbox e = new NotificationOutbox();
        e.id = UUID.randomUUID();
        e.type = Objects.requireNonNull(type);
        e.payload = Objects.requireNonNull(payload);
        e.eventKey = Objects.requireNonNull(eventKey);
        e.status  = EventStatus.PENDING;
        e.retryCount = 0;
        e.createdAt = LocalDateTime.now(ZoneId.systemDefault());
        e.nextAttemptAt = LocalDateTime.now(ZoneId.systemDefault());
        return e;
    }

    // ---- comandi di dominio (no setter esposti a caso) ----

    public void markProcessing() {
        this.status = EventStatus.PROCESSING;
        this.lastAttemptAt = LocalDateTime.now(ZoneId.systemDefault());
    }

    public void markSent() {
        this.status = EventStatus.SENT;
    }

    public void markFailed(String errorMessage) {
        this.status = EventStatus.FAILED;
        this.errorMessage = errorMessage;
    }

    public void scheduleRetry(String errorMessage) {
        this.retryCount++;
        this.errorMessage = errorMessage;
        this.status = EventStatus.PENDING;
        this.nextAttemptAt = LocalDateTime.now(ZoneId.systemDefault()).plusSeconds(5L * retryCount);
    }

    public boolean hasExceededRetryLimit(int maxRetries) {
        return this.retryCount >= maxRetries;
    }

    // ---- getters ----
    public UUID getId() { return id; }
    public String getType() { return type; }
    public String getPayload() { return payload; }
    public EventStatus getStatus() { return status; }
    public int getRetryCount() { return retryCount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getLastAttemptAt() { return lastAttemptAt; }
    public LocalDateTime getNextAttemptAt() { return nextAttemptAt; }
    public String getEventKey() { return eventKey; }
    public String getErrorMessage() { return errorMessage; }
    public long getVersion() { return version; }


}