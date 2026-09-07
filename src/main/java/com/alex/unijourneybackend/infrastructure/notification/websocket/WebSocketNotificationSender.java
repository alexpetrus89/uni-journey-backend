package com.alex.unijourneybackend.infrastructure.notification.websocket;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.infrastructure.web.registry.UniJourneyPathsRegistry;
import com.alex.unijourneybackend.infrastructure.websocket.WebSocketService;
import com.alex.unijourneybackend.modules.notification.port.out.NotificationSender;

@Component
public class WebSocketNotificationSender implements NotificationSender {

    private final WebSocketService service;
    private final UniJourneyPathsRegistry registry;

    public WebSocketNotificationSender(WebSocketService service, UniJourneyPathsRegistry registry) {
        this.service = service;
        this.registry = registry;
    }

    @Override
    public void send(String username, String subject, String message) {
        String topic = registry.getWebSocketTopics().resolveTopic(subject);
        service.sendWebSocketMessage(username, topic, message);
    }


}
