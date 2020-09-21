package com.moonactive.exam.arielshkalimexem.echoattime.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;

@Controller
public class RedisConfiguration {

    @Value("${redis.hostName}")
    private String hostName;

    @Value("${redis.port}")
    private int port;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory(new RedisStandaloneConfiguration(hostName, port));
    }

    @Bean(name = "zsetTemplate")
    RedisTemplate<String, Long> redisTemplateForZSet() {
        RedisTemplate<String, Long> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericToStringSerializer<>(Long.TYPE));
        template.afterPropertiesSet();
        return template;
    }

    @Bean(name = "listTemplate")
    RedisTemplate<String, String> redisTemplateForList() {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    ZSetOperations<String, Long> zOperations() {
        return redisTemplateForZSet().opsForZSet();
    }

    @Bean
    ListOperations<String, String> listOperations() {
        return redisTemplateForList().opsForList();
    }
}
