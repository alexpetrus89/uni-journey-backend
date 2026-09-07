package com.alex.unijourneybackend.modules.notification.application.service;

import java.util.List;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;

import com.alex.unijourneybackend.common.domain.exception.DataAccessServiceException;
import com.alex.unijourneybackend.modules.notification.domain.model.Notification;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;


public interface NotificationService {

    /**
     * Retrieves the active notifications for a specific student.
     * @param username the username for whom to retrieve notifications
     * @return a list of active notifications for the student
     * @throws DataAccessServiceException if there is an error accessing the database.
     */
    List<Notification> getActiveNotifications(String username) throws DataAccessServiceException;


    /**
     * Marks a notification as read.
     * @param notificationId the ID of the notification to mark as read
     * @throws DataAccessServiceException if there is an error accessing the database.
     */
    @Transactional
    @Retryable(retryFor = PersistenceException.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    void markAsRead(Long notificationId) throws DataAccessServiceException;


    /**
     * Pulizia notifiche scadute: viene eseguita ogni notte alle 2:00
     * @throws DataAccessServiceException
     */
    @Transactional
    @Scheduled(cron = "0 0 2 * * ?")
    void cleanExpiredNotifications() throws DataAccessServiceException;


}
