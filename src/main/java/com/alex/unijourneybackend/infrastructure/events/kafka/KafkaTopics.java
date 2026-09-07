package com.alex.unijourneybackend.infrastructure.events.kafka;


public final class KafkaTopics {
    public static final String DOMAIN_EVENTS = "unijourney.domain-events";
    public static final String DOMAIN_EVENTS_DLT = "unijourney.domain-events.dlt";

    public static final String HEADER_EVENT_TYPE = "eventType";
    public static final String HEADER_EVENT_KEY = "eventKey";

    private KafkaTopics() {}
}
