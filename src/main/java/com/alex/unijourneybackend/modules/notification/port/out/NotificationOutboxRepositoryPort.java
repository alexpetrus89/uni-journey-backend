package com.alex.unijourneybackend.modules.notification.port.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.modules.notification.infrastructure.outbox.EventStatus;
import com.alex.unijourneybackend.modules.notification.infrastructure.outbox.NotificationOutbox;

public interface NotificationOutboxRepositoryPort {

    /**
      * Retrieves a notification by their ID.
     * @param id The notification ID.
     * @return The notification.
     */
    @NonNull
    Optional<NotificationOutbox> findById(@NonNull UUID id);

    /**
     * Finds the top 50 notification events by status, ordered by creation date in ascending order.
     * @param status The status of the notification events to find.
     * @return A list of notification events matching the criteria.
     */
    @NonNull
    List<NotificationOutbox> findTop50ByStatusOrderByCreatedAtAsc(@NonNull EventStatus status);


    /**
     * @param limit
     * @return A list of notification events with status pending
     */
    @NonNull
    List<NotificationOutbox> lockBatch(int limit);


    /**
     * Checks if a notification event with the specified key exists.
     * @param eventKey The key of the notification event to check.
     * @return true if the event exists, false otherwise.
     */
    boolean existsByEventKey(@NonNull String eventKey);


    /**
     * Saves a notification event to the repository.
     * @param outbox The notification event to save.
     * @return notification outbox
     */
    @NonNull
    NotificationOutbox save(@NonNull NotificationOutbox outbox);


}
