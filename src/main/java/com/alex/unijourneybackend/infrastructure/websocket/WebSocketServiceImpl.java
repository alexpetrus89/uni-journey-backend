package com.alex.unijourneybackend.infrastructure.websocket;

import java.util.Objects;

import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;



@Service
public class WebSocketServiceImpl implements WebSocketService {

    private final SimpMessagingTemplate template;

    public WebSocketServiceImpl(SimpMessagingTemplate template) {
        this.template = template;
    }


    /**
     * Sends a WebSocket message to a specific user.
     * @param username
     * @param destination
     * @param message
     * @throws MessagingException if an error occurs while sending the message
     */
    @Override
    public void sendWebSocketMessage(String username, String destination, String message)
        throws MessagingException
    {
        template.convertAndSendToUser(
            Objects.requireNonNull(username),
            Objects.requireNonNull(destination),
            Objects.requireNonNull(message)
        );
    }

}
