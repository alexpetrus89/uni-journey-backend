package com.alex.unijourneybackend.modules.notification.infrastructure.handler;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.modules.notification.domain.event.FacultyNewsEvent;
import com.alex.unijourneybackend.modules.notification.infrastructure.delivery.NotificationDeliveryService;
import com.alex.unijourneybackend.modules.notification.infrastructure.handler.support.AbstractMultiRecipientNotificationEventHandler;
import com.alex.unijourneybackend.modules.user.domain.port.out.UserRepositoryPort;
import com.alex.unijourneybackend.modules.user.domain.valueobject.RoleType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FacultyNewsEventHandler extends AbstractMultiRecipientNotificationEventHandler<FacultyNewsEvent> {

    private final UserRepositoryPort port;

    public FacultyNewsEventHandler(
        ObjectMapper mapper,
        NotificationDeliveryService delivery,
        UserRepositoryPort port
    ) {
        super(mapper, delivery);
        this.port = port;
    }

    @Override public String getType() { return "FACULTY_NEWS"; }
    @Override protected Class<FacultyNewsEvent> eventClass() { return FacultyNewsEvent.class; }
    @Override protected int expiresInDays() { return 14; }

    @Override
    protected List<Recipient> resolveRecipients(FacultyNewsEvent event) {
        List<RoleType> roles = (event.targetRoles() == null || event.targetRoles().isEmpty())
            ? List.of(RoleType.STUDENT)
            : event.targetRoles();

        return roles.stream()
            .map(role -> Objects.requireNonNull(role, "role"))
            .flatMap(role -> port.findAllByRole(role, PageQuery.unpaged()).content().stream())
            // TODO: se event.degreeCourseName() != null, filtrare qui solo gli
            // studenti iscritti a quel corso di laurea.
            .map(user -> Recipient.of(user, event.payload()))
            .toList();
    }


}



// solo studenti (default)
// var event = new FacultyNewsEvent(title, message, "FACULTY_NEWS", null, null, Instant.now());

// studenti + professori di uno specifico corso di laurea
// var event = new FacultyNewsEvent(
//    title, message, "FACULTY_NEWS",
//   List.of(RoleType.STUDENT, RoleType.PROFESSOR),
//    "Informatica",
//    Instant.now()
//);

//domainEventPublisher.publish(List.of(event));