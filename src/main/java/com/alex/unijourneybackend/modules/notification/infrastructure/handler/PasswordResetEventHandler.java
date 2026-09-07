package com.alex.unijourneybackend.modules.notification.infrastructure.handler;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.notification.domain.event.PasswordResetEvent;
import com.alex.unijourneybackend.modules.notification.infrastructure.delivery.NotificationDeliveryService;
import com.alex.unijourneybackend.modules.notification.infrastructure.handler.support.AbstractNotificationEventHandler;
import com.alex.unijourneybackend.modules.user.domain.service.UserResolver;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PasswordResetEventHandler extends AbstractNotificationEventHandler<PasswordResetEvent> {

    public PasswordResetEventHandler(
        ObjectMapper mapper,
        NotificationDeliveryService delivery,
        UserResolver resolver
    ) {
        super(mapper, delivery, resolver);
    }

    @Override public String getType() { return "PASSWORD_RESET"; }
    @Override protected Class<PasswordResetEvent> eventClass() { return PasswordResetEvent.class; }
    @Override protected String resolveRecipientUsername(PasswordResetEvent event) { return event.username(); }


}

