package com.alex.unijourneybackend.modules.notification.infrastructure.handler;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.notification.domain.event.PasswordChangedEvent;
import com.alex.unijourneybackend.modules.notification.infrastructure.delivery.NotificationDeliveryService;
import com.alex.unijourneybackend.modules.notification.infrastructure.handler.support.AbstractNotificationEventHandler;
import com.alex.unijourneybackend.modules.user.domain.service.UserResolver;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PasswordChangedEventHandler extends AbstractNotificationEventHandler<PasswordChangedEvent> {

    public PasswordChangedEventHandler(
        ObjectMapper mapper,
        NotificationDeliveryService delivery,
        UserResolver resolver
    ) {
        super(mapper, delivery, resolver);
    }

    @Override public String getType() { return "PASSWORD_CHANGED"; }
    @Override protected Class<PasswordChangedEvent> eventClass() { return PasswordChangedEvent.class; }
    @Override protected String resolveRecipientUsername(PasswordChangedEvent event) { return event.username(); }


}