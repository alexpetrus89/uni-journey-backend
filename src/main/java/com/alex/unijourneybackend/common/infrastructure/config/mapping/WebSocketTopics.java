package com.alex.unijourneybackend.common.infrastructure.config.mapping;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;

@ConfigurationProperties
@Validated
public record WebSocketTopics(
    @NotEmpty Map<String, String> topics
) {
    public String resolveTopic(String eventType) {
        return topics.getOrDefault(eventType, "/topic/notifications");
    }

    public Map<String, String> getTopics() {
        return topics;
    }


}

