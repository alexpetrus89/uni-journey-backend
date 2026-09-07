package com.alex.unijourneybackend.modules.notification.infrastructure.outbox;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationOutboxRepository
    extends JpaRepository<NotificationOutbox, UUID> {

    List<NotificationOutbox> findTop50ByStatusOrderByCreatedAtAsc(EventStatus status);

    @Query(value = """
        SELECT * FROM communication.notifications_event
        WHERE status = 'PENDING'
            AND next_attempt_at <= now()
        ORDER BY created_at
        FOR UPDATE SKIP LOCKED
        LIMIT :limit
    """, nativeQuery = true)
    List<NotificationOutbox> lockBatch(@Param("limit") int limit);


    boolean existsByEventKey(String eventKey);


}
