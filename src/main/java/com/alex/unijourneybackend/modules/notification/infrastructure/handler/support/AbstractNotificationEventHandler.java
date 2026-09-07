package com.alex.unijourneybackend.modules.notification.infrastructure.handler.support;

import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.common.events.domain.model.DomainEvent;
import com.alex.unijourneybackend.modules.notification.infrastructure.delivery.NotificationDeliveryService;
import com.alex.unijourneybackend.modules.notification.infrastructure.outbox.NotificationOutboxHandler;
import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.alex.unijourneybackend.modules.user.domain.service.UserResolver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractNotificationEventHandler<E extends DomainEvent>
    implements NotificationOutboxHandler<E> {

    private static final int DEFAULT_EXPIRES_IN_DAYS = 3;

    protected final ObjectMapper mapper;
    protected final NotificationDeliveryService delivery;
    protected final UserResolver resolver;

    protected AbstractNotificationEventHandler(
        ObjectMapper mapper,
        NotificationDeliveryService delivery,
        UserResolver resolver
    ) {
        this.mapper = mapper;
        this.delivery = delivery;
        this.resolver = resolver;
    }

    protected abstract Class<E> eventClass();
    protected abstract String resolveRecipientUsername(E event);

    protected String resolveMessage(E event) {
        return event.getPayload();
    }

    protected int expiresInDays() {
        return DEFAULT_EXPIRES_IN_DAYS;
    }

    protected boolean sendEmail() {
        return true;
    }

    @Override
    @Transactional
    public void handle(String payload, String eventKey) {
        try {
            E event = mapper.readValue(payload, eventClass());
            User user = resolver.resolveByUsername(resolveRecipientUsername(event));
            delivery.deliver(user, getType(), resolveMessage(event), eventKey, expiresInDays(), sendEmail(), true);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to process event of type " + getType(), e);
        }
    }


}