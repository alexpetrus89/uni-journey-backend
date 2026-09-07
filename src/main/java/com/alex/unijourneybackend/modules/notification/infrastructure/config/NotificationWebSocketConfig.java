package com.alex.unijourneybackend.modules.notification.infrastructure.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alex.unijourneybackend.common.infrastructure.config.mapping.WebSocketTopics;
import com.alex.unijourneybackend.common.infrastructure.config.yaml.YamlPropertySourceFactory;

@Configuration
@PropertySource(value = "classpath:path/web-socket-topics.json", factory = YamlPropertySourceFactory.class)
@EnableConfigurationProperties(WebSocketTopics.class)
public class NotificationWebSocketConfig {}
