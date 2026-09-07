package com.alex.unijourneybackend.common.infrastructure.config.paths;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alex.unijourneybackend.common.infrastructure.config.mapping.PathsByModuleMappings;
import com.alex.unijourneybackend.common.infrastructure.config.mapping.PathsByRoleMappings;
import com.alex.unijourneybackend.common.infrastructure.config.yaml.YamlPropertySourceFactory;


@Configuration
@PropertySource(value = "classpath:path/paths-by-role-mappings.json", factory = YamlPropertySourceFactory.class)
@PropertySource(value = "classpath:path/paths-by-module-mappings.json", factory = YamlPropertySourceFactory.class)
@EnableConfigurationProperties({ PathsByRoleMappings.class, PathsByModuleMappings.class })
public class UniJourneyPathsConfig {}