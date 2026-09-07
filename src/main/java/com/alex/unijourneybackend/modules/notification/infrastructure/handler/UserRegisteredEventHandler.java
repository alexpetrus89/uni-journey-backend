package com.alex.unijourneybackend.modules.notification.infrastructure.handler;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.notification.domain.event.UserRegisteredEvent;
import com.alex.unijourneybackend.modules.notification.infrastructure.delivery.NotificationDeliveryService;
import com.alex.unijourneybackend.modules.notification.infrastructure.handler.support.AbstractNotificationEventHandler;
import com.alex.unijourneybackend.modules.user.domain.service.UserResolver;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UserRegisteredEventHandler extends AbstractNotificationEventHandler<UserRegisteredEvent> {

    public UserRegisteredEventHandler(
        ObjectMapper mapper,
        NotificationDeliveryService delivery,
        UserResolver resolver
    ) {
        super(mapper, delivery, resolver);
    }

    @Override public String getType() { return "USER_REGISTERED"; }
    @Override protected Class<UserRegisteredEvent> eventClass() { return UserRegisteredEvent.class; }
    @Override protected String resolveRecipientUsername(UserRegisteredEvent event) { return event.username(); }


}


