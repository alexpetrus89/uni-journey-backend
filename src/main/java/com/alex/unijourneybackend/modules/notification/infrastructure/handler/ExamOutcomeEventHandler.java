package com.alex.unijourneybackend.modules.notification.infrastructure.handler;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.notification.domain.event.ExamOutcomeEvent;
import com.alex.unijourneybackend.modules.notification.infrastructure.delivery.NotificationDeliveryService;
import com.alex.unijourneybackend.modules.notification.infrastructure.handler.support.AbstractNotificationEventHandler;
import com.alex.unijourneybackend.modules.user.domain.service.UserResolver;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ExamOutcomeEventHandler extends AbstractNotificationEventHandler<ExamOutcomeEvent> {

    public ExamOutcomeEventHandler(
        ObjectMapper mapper,
        NotificationDeliveryService delivery,
        UserResolver resolver
    ) {
        super(mapper, delivery, resolver);
    }

    @Override public String getType() { return "EXAM_OUTCOME"; }
    @Override protected Class<ExamOutcomeEvent> eventClass() { return ExamOutcomeEvent.class; }
    @Override protected String resolveRecipientUsername(ExamOutcomeEvent event) { return event.username(); }


}


