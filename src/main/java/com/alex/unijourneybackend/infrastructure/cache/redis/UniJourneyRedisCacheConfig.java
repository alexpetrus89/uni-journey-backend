package com.alex.unijourneybackend.infrastructure.cache.redis;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
public class UniJourneyRedisCacheConfig {

    /**
     * Configura un CacheManager personalizzato per Redis.
     * - Serializza in JSON (leggibile)
     * - Imposta TTL specifici per ciascuna cache
     */
    @Bean
    @SuppressWarnings("unused")
    CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {

        // 🔹 Serializzatore chiavi (String)
        StringRedisSerializer keySerializer = new StringRedisSerializer();

        // 🔹 Configurazione ObjectMapper per JSON
        ObjectMapper objectMapper = new ObjectMapper();

        // 🔹 Serializzatore valori (JSON)
        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer(
            Objects.requireNonNull(
                objectMapper
                    .setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
                    .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY))
        );

        // 🔹 Configurazione base (default)
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration
            .defaultCacheConfig()
            .entryTtl(Objects.requireNonNull(Duration.ofMinutes(10))) // TTL default = 10 minuti
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer))
            .disableCachingNullValues();

        // 🔹 TTL personalizzati per cache specifiche
        Map<String, RedisCacheConfiguration> cacheConfigs = new HashMap<>();
        cacheConfigs.put("degreeCourses", defaultConfig.entryTtl(Objects.requireNonNull(Duration.ofMinutes(30)))); // cache dei corsi di laurea dura 30 min

        // 🔹 Creazione del CacheManager
        return RedisCacheManager
            .builder(Objects.requireNonNull(redisConnectionFactory))
            .cacheDefaults(defaultConfig)
            .withInitialCacheConfigurations(cacheConfigs)
            .transactionAware()
            .build();
    }


}

