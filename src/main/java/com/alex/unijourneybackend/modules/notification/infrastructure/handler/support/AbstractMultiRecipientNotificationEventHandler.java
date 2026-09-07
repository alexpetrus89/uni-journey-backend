package com.alex.unijourneybackend.modules.notification.infrastructure.handler.support;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.common.events.domain.model.DomainEvent;
import com.alex.unijourneybackend.modules.notification.infrastructure.delivery.NotificationDeliveryService;
import com.alex.unijourneybackend.modules.notification.infrastructure.outbox.NotificationOutboxHandler;
import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractMultiRecipientNotificationEventHandler<E extends DomainEvent>
    implements NotificationOutboxHandler<E> {

    private static final int DEFAULT_EXPIRES_IN_DAYS = 3;

    protected final ObjectMapper mapper;
    protected final NotificationDeliveryService delivery;

    protected AbstractMultiRecipientNotificationEventHandler(
        ObjectMapper mapper,
        NotificationDeliveryService delivery
    ) {
        this.mapper = mapper;
        this.delivery = delivery;
    }

    protected abstract Class<E> eventClass();
    protected abstract List<Recipient> resolveRecipients(E event);

    protected int expiresInDays() {
        return DEFAULT_EXPIRES_IN_DAYS;
    }

    @Override
    @Transactional
    public void handle(String payload, String eventKey) {
        try {
            E event = mapper.readValue(payload, eventClass());
            resolveRecipients(event).forEach(r ->
                delivery.deliver(r.user(), getType(), r.message(), eventKey, expiresInDays(), r.sendEmail(), r.sendWebSocket()));
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to process event of type " + getType(), e);
        }
    }

    public record Recipient(User user, String message, boolean sendEmail, boolean sendWebSocket) {
        public static Recipient of(User user, String message) {
            return new Recipient(user, message, true, true);
        }
        public static Recipient emailOnly(User user, String message) {
            return new Recipient(user, message, true, false);
        }
        public static Recipient webSocketOnly(User user, String message) {
            return new Recipient(user, message, false, true);
        }
    }


}