package com.alex.unijourneybackend.infrastructure.events.kafka;

import java.util.Objects;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.ExponentialBackOffWithMaxRetries;


@Configuration
public class KafkaTopicConfig {

    @Bean
    @SuppressWarnings("unused")
    NewTopic domainEventsTopic() {
        return TopicBuilder.name(KafkaTopics.DOMAIN_EVENTS)
            .partitions(3)
            .replicas(1)
            .build();
    }

    @Bean
    @SuppressWarnings("unused")
    NewTopic domainEventsDltTopic() {
        return TopicBuilder.name(KafkaTopics.DOMAIN_EVENTS_DLT)
            .partitions(1)
            .replicas(1)
            .build();
    }


    @Bean
    @SuppressWarnings("unused")
    DefaultErrorHandler kafkaErrorHandler(KafkaTemplate<String, String> template) {
        Objects.requireNonNull(template);
        var recoverer = new DeadLetterPublishingRecoverer(
            template,
            (eventRecord, ex) -> new TopicPartition(KafkaTopics.DOMAIN_EVENTS_DLT, eventRecord.partition())
        );

        var backoff = new ExponentialBackOffWithMaxRetries(5);
        backoff.setInitialInterval(1000L);
        backoff.setMultiplier(2.0);
        backoff.setMaxInterval(30000L);

        return new DefaultErrorHandler(recoverer, backoff);
    }


}
