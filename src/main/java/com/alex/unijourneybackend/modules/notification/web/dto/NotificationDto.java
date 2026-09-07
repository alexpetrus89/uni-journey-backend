package com.alex.unijourneybackend.modules.notification.web.dto;

import java.time.LocalDateTime;

import com.alex.unijourneybackend.modules.notification.domain.model.Notification;



public record NotificationDto(
    Long id,
    String type,
    String message,
    LocalDateTime createdAt,
    LocalDateTime expiresAt,
    boolean read
) {
    public static NotificationDto toDto(Notification notification) {
        if (notification == null) return null;
        return new NotificationDto(
            notification.getId(),
            notification.getType(),
            notification.getMessage(),
            notification.getCreatedAt(),
            notification.getExpiresAt(),
            notification.isRead()
        );
    }
}

