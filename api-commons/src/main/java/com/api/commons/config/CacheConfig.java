package com.api.commons.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
@EnableCaching
@ConditionalOnClass(ReactiveRedisTemplate.class)
@Profile("!default")
public class CacheConfig extends CachingConfigurerSupport {

  @Bean
  public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
    // config for env != localhost
    return new LettuceConnectionFactory("host", 8080);
  }

  @Bean
  public ReactiveRedisTemplate<String, String> reactiveRedisTemplate
      (ReactiveRedisConnectionFactory connectionFactory) {
    return new ReactiveRedisTemplate<>(connectionFactory, RedisSerializationContext.string());
  }
}
