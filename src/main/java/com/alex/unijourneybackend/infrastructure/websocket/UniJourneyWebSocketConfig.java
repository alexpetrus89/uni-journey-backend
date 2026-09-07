package com.alex.unijourneybackend.infrastructure.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class UniJourneyWebSocketConfig implements WebSocketMessageBrokerConfigurer{

    @Override
    public void configureMessageBroker(@NonNull MessageBrokerRegistry config){
        // Prefix for the broker (client -> server)
        /**
         * /unj — tutto ciò che il client Angular manda al server.
         * Se Angular volesse inviare un messaggio a Spring scriverebbe su /unj/qualcosa.
         * Nel tuo caso non lo usi perché le notifiche vanno solo da server a client, non viceversa.
         */
        config.setApplicationDestinationPrefixes("/unj");

        // Prefix for the internal topic/broker (server -> client)
        /**
         * /topic — il broker interno di Spring.
         * Quando Spring pubblica un messaggio su /topic/exam-outcome,
         * lo manda a tutti i client iscritti a quel topic (broadcast).
         */
        config.enableSimpleBroker("/topic");

        // Prefix for the public topic/broker (server -> client)
        /**
         * /user — prefisso speciale di STOMP.
         * Quando Spring pubblica su /user/{username}/topic/exam-outcome,
         * il messaggio arriva solo a quell'utente specifico.
         * Angular si iscrive a /user/topic/exam-outcome e Spring internamente
         * risolve l'username dalla sessione.
         */
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(@NonNull StompEndpointRegistry registry) {
        registry
            .addEndpoint("/api/v1/ws")
            .setAllowedOriginPatterns("http://localhost:4200")
            .withSockJS();
    }


}
