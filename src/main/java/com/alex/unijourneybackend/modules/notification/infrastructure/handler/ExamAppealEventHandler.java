package com.alex.unijourneybackend.modules.notification.infrastructure.handler;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.notification.domain.event.ExamAppealScheduledEvent;
import com.alex.unijourneybackend.modules.notification.infrastructure.delivery.NotificationDeliveryService;
import com.alex.unijourneybackend.modules.notification.infrastructure.handler.support.AbstractNotificationEventHandler;
import com.alex.unijourneybackend.modules.user.domain.service.UserResolver;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ExamAppealEventHandler extends AbstractNotificationEventHandler<ExamAppealScheduledEvent> {

    public ExamAppealEventHandler(
        ObjectMapper mapper,
        NotificationDeliveryService delivery,
        UserResolver resolver
    ) {
        super(mapper, delivery, resolver);
    }

    @Override public String getType() { return "EXAM_APPEAL_SCHEDULED"; }
    @Override protected Class<ExamAppealScheduledEvent> eventClass() { return ExamAppealScheduledEvent.class; }
    @Override protected String resolveRecipientUsername(ExamAppealScheduledEvent event) { return event.username(); }
    @Override protected String resolveMessage(ExamAppealScheduledEvent event) { return event.date().toString(); }


}