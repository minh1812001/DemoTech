package com.example.demo.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.Random;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        // Cấu hình mặc định
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .disableCachingNullValues();

        // Tạo một CacheManager tùy chỉnh ghi đè phương thức tạo cache
        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory),
                config
        ) {
            @Override
            protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
                // Mỗi khi một cache mới được tạo (ví dụ "orders"), ta gán cho nó một TTL ngẫu nhiên
                // Ví dụ: Base TTL là 10 phút, cộng thêm ngẫu nhiên từ 1 đến 60 giây
                long randomSuffix = new Random().nextInt(60);
                Duration ttl = Duration.ofMinutes(5).plusSeconds(randomSuffix);

                return super.createRedisCache(name, cacheConfig.entryTtl(ttl));
            }
        };
    }
}