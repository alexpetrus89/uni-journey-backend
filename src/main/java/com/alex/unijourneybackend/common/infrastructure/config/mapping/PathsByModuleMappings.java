package com.alex.unijourneybackend.common.infrastructure.config.mapping;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;

@ConfigurationProperties
@Validated
public record PathsByModuleMappings(
    @NotEmpty Map<String, List<String>> pathsByModule
) {}

