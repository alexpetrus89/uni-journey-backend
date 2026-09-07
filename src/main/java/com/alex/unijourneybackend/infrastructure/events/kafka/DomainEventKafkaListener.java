package com.alex.unijourneybackend.infrastructure.events.kafka;

import java.nio.charset.StandardCharsets;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.notification.infrastructure.outbox.NotificationOutboxDispatcher;

@Component
public class DomainEventKafkaListener {

    private final NotificationOutboxDispatcher dispatcher;

    public DomainEventKafkaListener(NotificationOutboxDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @KafkaListener(topics = KafkaTopics.DOMAIN_EVENTS, groupId = "unijourney-notification-consumer")
    public void onMessage(ConsumerRecord<String, String> consumerRecord, Acknowledgment ack) {
        String type = new String(consumerRecord.headers().lastHeader(KafkaTopics.HEADER_EVENT_TYPE).value(), StandardCharsets.UTF_8);
        dispatcher.dispatch(type, consumerRecord.value(), consumerRecord.key());
        ack.acknowledge();
    }


}
