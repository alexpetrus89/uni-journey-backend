package com.alex.unijourneybackend.modules.notification.application.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.common.domain.exception.DataAccessServiceException;
import com.alex.unijourneybackend.modules.notification.domain.model.Notification;
import com.alex.unijourneybackend.modules.notification.port.out.NotificationRepositoryPort;
import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.alex.unijourneybackend.modules.user.domain.service.UserResolver;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@Service
public class NotificationServiceImpl implements NotificationService {

    // instance variables
    private final NotificationRepositoryPort port;
    private final UserResolver resolver;

    public NotificationServiceImpl(
        NotificationRepositoryPort port,
        UserResolver resolver
    ) {
        this.port = port;
        this.resolver = resolver;
    }


    /**
     * Retrieves the active notifications for a specific student.
     * @param username the username for whom to retrieve notifications
     * @return a list of active notifications for the student
     * @throws DataAccessServiceException if there is an error accessing the database.
     */
    @Override
    public List<Notification> getActiveNotifications(String username) throws DataAccessServiceException {
        User user = resolver.resolveByUsername(username);
        Objects.requireNonNull(user);
        LocalDateTime time = Objects.requireNonNull(LocalDateTime.now(ZoneId.systemDefault()));
        try {
            return port.findByUserAndReadFalseAndExpiresAtAfter(user, time);
        } catch (PersistenceException e) {
            throw new DataAccessServiceException("Error accessing database for fetching notifications: ", e);
        }
    }


    /**
     * Marks a notification as read.
     * @param notificationId the ID of the notification to mark as read
     * @throws DataAccessServiceException if there is an error accessing the database.
     */
    @Override
    @Transactional
    @Retryable(retryFor = PersistenceException.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public void markAsRead(Long notificationId) throws DataAccessServiceException {
        Objects.requireNonNull(notificationId, "Notification ID cannot be null");
        try {
            port
                .findById(notificationId)
                .ifPresent(n -> { n.markAsRead(); port.save(n); });
        } catch (PersistenceException e) {
            throw new DataAccessServiceException("Error accessing database for marking notification as read: ", e);
        }
    }


    /**
     * Pulizia notifiche scadute: viene eseguita ogni notte alle 2:00
     * @throws DataAccessServiceException
     */
    @Override
    @Transactional
    @Scheduled(cron = "0 0 2 * * ?")
    public void cleanExpiredNotifications() throws DataAccessServiceException {
        LocalDateTime time = Objects.requireNonNull(LocalDateTime.now(ZoneId.systemDefault()));
        try {
            port.deleteByExpiresAtBefore(time);
        } catch (PersistenceException e) {
            throw new DataAccessServiceException("Error accessing database for cleaning expired notifications: ", e);
        }
    }


}

