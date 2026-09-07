package com.alex.unijourneybackend.common.infrastructure.config.yaml;


import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class YamlPropertySourceFactory extends DefaultPropertySourceFactory {

    @Override
    @NonNull
    public PropertySource<?> createPropertySource(@Nullable String name, @NonNull EncodedResource resource)
        throws IOException {

        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(resource.getResource());
        Properties properties = Objects.requireNonNull(factory.getObject());

        String sourceName = Objects.requireNonNull(
            name != null ? name : resource.getResource().getFilename());
        return new PropertiesPropertySource(sourceName, properties);
    }
}