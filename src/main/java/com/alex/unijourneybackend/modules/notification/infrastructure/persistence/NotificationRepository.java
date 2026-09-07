package com.alex.unijourneybackend.modules.notification.infrastructure.persistence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.alex.unijourneybackend.modules.notification.domain.model.Notification;
import com.alex.unijourneybackend.modules.user.domain.model.User;

import jakarta.persistence.PersistenceException;


public interface NotificationRepository
    extends JpaRepository<Notification, Long>{

    /**
     * Retrieve all student's notification
     * @param user
     * @param now
     * @return Outcome notification
     * @throws PersistenceException persistence error
     */
    List<Notification> findByUserAndReadFalseAndExpiresAtAfter(User user, LocalDateTime now);


    /**
     * Delete all expired notifications
     * @param now
     * @throws PersistenceException persistence error
     */
    @Modifying
    void deleteByExpiresAtBefore(LocalDateTime now);


}
