package com.alex.unijourneybackend.modules.notification.infrastructure.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.modules.notification.domain.event.AccountLockedEvent;
import com.alex.unijourneybackend.modules.notification.infrastructure.delivery.NotificationDeliveryService;
import com.alex.unijourneybackend.modules.notification.infrastructure.handler.support.AbstractMultiRecipientNotificationEventHandler;
import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.alex.unijourneybackend.modules.user.domain.port.out.UserRepositoryPort;
import com.alex.unijourneybackend.modules.user.domain.service.UserResolver;
import com.alex.unijourneybackend.modules.user.domain.valueobject.RoleType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AccountLockedEventHandler extends AbstractMultiRecipientNotificationEventHandler<AccountLockedEvent> {

    private static final String OWNER_MESSAGE =
        "Your account has been locked due to too many failed login attempts. Please contact support to unlock it.";

    private final UserResolver resolver;
    private final UserRepositoryPort port;

    public AccountLockedEventHandler(
        ObjectMapper mapper,
        NotificationDeliveryService delivery,
        UserResolver resolver,
        UserRepositoryPort port
    ) {
        super(mapper, delivery);
        this.resolver = resolver;
        this.port = port;
    }

    @Override public String getType() { return "ACCOUNT_LOCKED"; }
    @Override protected Class<AccountLockedEvent> eventClass() { return AccountLockedEvent.class; }
    @Override protected int expiresInDays() { return 7; }

    @Override
    protected List<Recipient> resolveRecipients(AccountLockedEvent event) {
        List<Recipient> recipients = new ArrayList<>();

        User owner = resolver.resolveByUsername(event.username());
        recipients.add(Recipient.emailOnly(owner, OWNER_MESSAGE));

        String adminMessage = "Account locked: " + event.username();
        port.findAllByRole(RoleType.ADMIN, new PageQuery(0, Integer.MAX_VALUE))
            .content()
            .forEach(admin -> recipients.add(Recipient.webSocketOnly(admin, adminMessage)));

        return recipients;
    }


}
