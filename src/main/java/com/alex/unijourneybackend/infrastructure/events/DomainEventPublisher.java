package com.alex.unijourneybackend.infrastructure.events;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.common.events.domain.model.DomainEvent;
import com.alex.unijourneybackend.modules.notification.infrastructure.outbox.NotificationOutbox;
import com.alex.unijourneybackend.modules.notification.port.out.NotificationOutboxRepositoryPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.codec.digest.DigestUtils;

@Component
public class DomainEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(DomainEventPublisher.class);

    private final NotificationOutboxRepositoryPort port;
    private final ObjectMapper mapper;

    public DomainEventPublisher(NotificationOutboxRepositoryPort port, ObjectMapper mapper) {
        this.port = port;
        this.mapper = mapper;
    }

    /**
     * Publishes a list of domain events
     * @param events the list of domain events to publish
     */
    @Transactional
    public void publish(List<DomainEvent> events) {
        events.forEach(this::saveToOutbox);
    }


    private void saveToOutbox(DomainEvent event) {
        try {
            String payload  = Objects.requireNonNull(mapper.writeValueAsString(event));
            String eventKey = Objects.requireNonNull(buildEventKey(event, payload));

            if (port.existsByEventKey(eventKey)) {
                log.debug("Duplicate event ignored: type={} key={}", event.getType(), eventKey);
                return;
            }

            NotificationOutbox outbox = Objects.requireNonNull(NotificationOutbox.create(event.getType(), payload, eventKey));
            port.save(outbox);

        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Event serialization failed: " + event.getType(), e);
        }
    }


    private String buildEventKey(DomainEvent event, String payload) {
        byte[] bytes = payload.getBytes(StandardCharsets.UTF_8);
        return event.getType() + ":" + DigestUtils.sha256Hex(bytes);
    }


}
