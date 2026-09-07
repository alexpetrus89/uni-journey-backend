package com.alex.unijourneybackend.modules.notification.infrastructure.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.modules.notification.domain.model.Notification;
import com.alex.unijourneybackend.modules.notification.port.out.NotificationRepositoryPort;
import com.alex.unijourneybackend.modules.user.domain.model.User;

@Service
public class NotificationRepositoryAdapter implements NotificationRepositoryPort {

    private final NotificationRepository repository;

    public NotificationRepositoryAdapter(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    @SuppressWarnings("null")
    public Optional<Notification> findById(Long id) {
        return repository.findById(id); // JpaRepository già restituisce Optional<T>
    }

    @Override
    @SuppressWarnings("null")
    public Notification save(Notification notification) {
        return repository.save(notification);
    }

    @Override
    @SuppressWarnings("null")
    public List<Notification> findByUserAndReadFalseAndExpiresAtAfter(User user, LocalDateTime now) {
        return repository.findByUserAndReadFalseAndExpiresAtAfter(user, now);
    }

    @Override
    @SuppressWarnings("null")
    public void deleteByExpiresAtBefore(LocalDateTime now) {
        repository.deleteByExpiresAtBefore(now);
    }


}
