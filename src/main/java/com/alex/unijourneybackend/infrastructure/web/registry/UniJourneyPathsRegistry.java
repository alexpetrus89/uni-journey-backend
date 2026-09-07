package com.alex.unijourneybackend.infrastructure.web.registry;


import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.common.infrastructure.config.mapping.PathsByModuleMappings;
import com.alex.unijourneybackend.common.infrastructure.config.mapping.PathsByRoleMappings;
import com.alex.unijourneybackend.common.infrastructure.config.mapping.WebSocketTopics;


// ✅ UniJourneyViewsRegistry diventa solo un holder
@Component
public class UniJourneyPathsRegistry {

    private final PathsByRoleMappings roleMappings;
    private final PathsByModuleMappings moduleMappings;
    private final WebSocketTopics topics;

    public UniJourneyPathsRegistry(
        PathsByRoleMappings roleMappings,
        PathsByModuleMappings moduleMappings,
        WebSocketTopics topics
    ) {
        this.roleMappings = roleMappings;
        this.moduleMappings = moduleMappings;
        this.topics = topics;
    }

    public PathsByRoleMappings getPathsByRoleMappings()   { return roleMappings; }
    public PathsByModuleMappings getPathsByModuleMappings() { return moduleMappings; }
    public WebSocketTopics getWebSocketTopics() { return topics; }
    public String getWebSocketUrl() { return "/api/v1/ws/**"; }
}

