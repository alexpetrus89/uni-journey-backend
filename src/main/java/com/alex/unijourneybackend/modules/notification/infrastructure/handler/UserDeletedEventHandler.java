package com.alex.unijourneybackend.modules.notification.infrastructure.handler;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.infrastructure.notification.email.EmailNotificationSender;
import com.alex.unijourneybackend.modules.notification.domain.event.UserDeletedEvent;
import com.alex.unijourneybackend.modules.notification.infrastructure.outbox.NotificationOutboxHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Non persiste una Notification: l'utente è già stato rimosso dal DB,
 * quindi un riferimento FK verso di lui violerebbe l'integrità referenziale.
 * Unico side-effect: email di conferma cancellazione.
 */
@Component
public class UserDeletedEventHandler implements NotificationOutboxHandler<UserDeletedEvent> {

    private final ObjectMapper mapper;
    private final EmailNotificationSender sender;

    public UserDeletedEventHandler(ObjectMapper mapper, EmailNotificationSender sender) {
        this.mapper = mapper;
        this.sender = sender;
    }

    @Override
    public String getType() { return "USER_DELETED"; }

    @Override
    public void handle(String payload, String eventKey) {
        try {
            UserDeletedEvent event = mapper.readValue(payload, UserDeletedEvent.class);
            sender.send(event.username(), getType(), event.payload());
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to process user deleted event", e);
        }
    }


}