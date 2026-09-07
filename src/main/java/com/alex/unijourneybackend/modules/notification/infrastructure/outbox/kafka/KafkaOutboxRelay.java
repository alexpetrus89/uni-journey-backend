package com.alex.unijourneybackend.modules.notification.infrastructure.outbox.kafka;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.infrastructure.events.kafka.KafkaTopics;
import com.alex.unijourneybackend.modules.notification.infrastructure.outbox.NotificationOutbox;

@Component
public class KafkaOutboxRelay {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(KafkaOutboxRelay.class);
    private final NotificationOutboxTransactionalOps ops;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaOutboxRelay(NotificationOutboxTransactionalOps ops, KafkaTemplate<String, String> kafkaTemplate) {
        this.ops = ops;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void relay() {
        List<NotificationOutbox> events = ops.lockBatch();

        for (NotificationOutbox event : events)
            publish(event);
    }

    private void publish(NotificationOutbox event) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(
            KafkaTopics.DOMAIN_EVENTS, event.getEventKey(), event.getPayload());
        producerRecord.headers().add(KafkaTopics.HEADER_EVENT_TYPE, event.getType().getBytes(StandardCharsets.UTF_8));

        kafkaTemplate.send(producerRecord).whenComplete((result, ex) -> {
            if (ex == null) {
                ops.markSent(event.getId());
                log.info("Published to Kafka: type={} key={}", event.getType(), event.getEventKey());
            } else {
                ops.markFailedOrRetry(event.getId(), ex.getMessage());
            }
        });
    }


}

