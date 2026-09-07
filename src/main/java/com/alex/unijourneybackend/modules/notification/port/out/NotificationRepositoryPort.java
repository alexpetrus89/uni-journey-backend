package com.alex.unijourneybackend.modules.notification.port.out;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.modules.notification.domain.model.Notification;
import com.alex.unijourneybackend.modules.user.domain.model.User;


public interface NotificationRepositoryPort {

    /**
     * Finds a notification by its ID.
     * @param id the notification ID
     * @return Optional<Notification>
     */
    @NonNull
    Optional<Notification> findById(@NonNull Long id);


    /**
     * Save notification.
     * @param notification
     * @return Notification
     */
    @NonNull
    Notification save(@NonNull Notification notification);


    /**
     * Retrieves all unread and non-expired notifications for a user.
     * @param user the user
     * @param now the current datetime used as expiry threshold
     * @return list of active notifications
     */
    @NonNull
    List<Notification> findByUserAndReadFalseAndExpiresAtAfter(@NonNull User user, @NonNull LocalDateTime now);


    /**
     * Deletes all notifications expired before the given datetime.
     * @param now the expiry threshold
     */
    void deleteByExpiresAtBefore(@NonNull LocalDateTime now);


}
