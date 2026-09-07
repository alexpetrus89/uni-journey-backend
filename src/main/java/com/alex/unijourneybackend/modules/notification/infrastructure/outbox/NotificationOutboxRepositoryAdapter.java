package com.alex.unijourneybackend.modules.notification.infrastructure.outbox;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.modules.notification.port.out.NotificationOutboxRepositoryPort;

@Service
public class NotificationOutboxRepositoryAdapter implements NotificationOutboxRepositoryPort {

    private final NotificationOutboxRepository repository;

    public NotificationOutboxRepositoryAdapter(NotificationOutboxRepository repository) {
        this.repository = repository;
    }

    @Override
    @SuppressWarnings("null") // null-safety garantita dal contratto dichiarato nel port
    public Optional<NotificationOutbox> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    @SuppressWarnings("null")
    public List<NotificationOutbox> findTop50ByStatusOrderByCreatedAtAsc(EventStatus status) {
        return repository.findTop50ByStatusOrderByCreatedAtAsc(status);
    }

    @Override
    @SuppressWarnings("null")
    public List<NotificationOutbox> lockBatch(@Param("limit") int limit) {
        return repository.lockBatch(limit);
    }

    @Override
    @SuppressWarnings("null")
    public boolean existsByEventKey(String eventKey) {
        return repository.existsByEventKey(eventKey);
    }

    @Override
    @SuppressWarnings("null")
    public NotificationOutbox save(NotificationOutbox outbox) {
        Objects.requireNonNull(outbox, "NotificationOutbox cannot be null");
        return repository.save(outbox);
    }


}
