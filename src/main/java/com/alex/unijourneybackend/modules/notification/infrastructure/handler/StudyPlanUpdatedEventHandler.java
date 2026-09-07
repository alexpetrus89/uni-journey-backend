package com.alex.unijourneybackend.modules.notification.infrastructure.handler;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.notification.domain.event.StudyPlanUpdatedEvent;
import com.alex.unijourneybackend.modules.notification.infrastructure.delivery.NotificationDeliveryService;
import com.alex.unijourneybackend.modules.notification.infrastructure.handler.support.AbstractNotificationEventHandler;
import com.alex.unijourneybackend.modules.user.domain.service.UserResolver;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class StudyPlanUpdatedEventHandler extends AbstractNotificationEventHandler<StudyPlanUpdatedEvent> {

    public StudyPlanUpdatedEventHandler(
        ObjectMapper mapper,
        NotificationDeliveryService delivery,
        UserResolver resolver
    ) {
        super(mapper, delivery, resolver);
    }

    @Override public String getType() { return "STUDY_PLAN_UPDATED"; }
    @Override protected Class<StudyPlanUpdatedEvent> eventClass() { return StudyPlanUpdatedEvent.class; }
    @Override protected String resolveRecipientUsername(StudyPlanUpdatedEvent event) { return event.username(); }


}
