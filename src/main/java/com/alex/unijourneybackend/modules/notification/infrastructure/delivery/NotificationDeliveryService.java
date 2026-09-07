package com.alex.unijourneybackend.modules.notification.infrastructure.delivery;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.infrastructure.notification.email.EmailNotificationSender;
import com.alex.unijourneybackend.infrastructure.notification.websocket.WebSocketNotificationSender;
import com.alex.unijourneybackend.modules.notification.domain.model.Notification;
import com.alex.unijourneybackend.modules.notification.port.out.NotificationRepositoryPort;
import com.alex.unijourneybackend.modules.notification.web.dto.NotificationDto;
import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Punto unico di persistenza + consegna (email/websocket) per le notifiche.
 * Incapsula idempotenza (event_key) e isolamento dei fallimenti di delivery
 * dal commit della notifica.
 */
@Service
public class NotificationDeliveryService {

    private static final Logger log = LoggerFactory.getLogger(NotificationDeliveryService.class);

    private final ObjectMapper mapper;
    private final EmailNotificationSender emailSender;
    private final WebSocketNotificationSender webSocketSender;
    private final NotificationRepositoryPort port;

    public NotificationDeliveryService(
        ObjectMapper mapper,
        EmailNotificationSender emailSender,
        WebSocketNotificationSender webSocketSender,
        NotificationRepositoryPort port
    ) {
        this.mapper = mapper;
        this.emailSender = emailSender;
        this.webSocketSender = webSocketSender;
        this.port = port;
    }

    /**
     * Persiste la notifica e, se non è un duplicato, invia i canali richiesti.
     * @return true se la notifica è stata effettivamente creata (non un duplicato)
     */
    @Transactional(propagation = org.springframework.transaction.annotation.Propagation.MANDATORY)
    public boolean deliver(
        User recipient,
        String type,
        String message,
        String eventKey,
        int expiresInDays,
        boolean sendEmail,
        boolean sendWebSocket
    ) {
        Notification notification = Objects.requireNonNull(
            Notification.create(recipient, type, message, eventKey, expiresInDays));

        Notification saved;
        try {
            saved = port.save(notification);
        } catch (DataIntegrityViolationException _) {
            log.info("Duplicate event ignored (idempotency): type={} key={} user={}",
                type, eventKey, recipient.getUsername());
            return false; // già processato per questo utente, non rimando email/websocket
        }

        if (sendWebSocket) sendWebSocketSafely(recipient.getUsername(), type, saved);
        if (sendEmail) sendEmailSafely(recipient.getUsername(), type, message);
        return true;
    }

    private void sendWebSocketSafely(String username, String type, Notification saved) {
        try {
            webSocketSender.send(username, type, toJson(NotificationDto.toDto(saved)));
        } catch (Exception e) {
            log.error("WebSocket delivery failed for type={} user={}: {}", type, username, e.getMessage());
            // non rilancio: non deve far fallire l'email né il commit della Notification
        }
    }

    private void sendEmailSafely(String username, String type, String message) {
        try {
            emailSender.send(username, formatNotificationType(type), message);
        } catch (Exception e) {
            log.error("Email delivery failed for type={} user={}: {}", type, username, e.getMessage());
            // idem
        }
    }

    private String toJson(NotificationDto dto) {
        try {
            return mapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to serialize notification dto", e);
        }
    }

    private String formatNotificationType(String value) {
        if (value == null || value.isBlank()) return value;

        String result = java.util.Arrays.stream(value.split("_"))
            .filter(word -> !word.isBlank())
            .map(word -> Objects.requireNonNull(word).toLowerCase())
            .collect(java.util.stream.Collectors.joining(" "));

        return result.isEmpty()
            ? result
            : Character.toUpperCase(result.charAt(0)) + result.substring(1) + ".";
    }


}
