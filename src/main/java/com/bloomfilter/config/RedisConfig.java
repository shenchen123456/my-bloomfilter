package com.bloomfilter.config;

import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import io.netty.util.CharsetUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.UUID;

/**
 * @Author: shenchen
 * @Data: 2020/2/14
 * @Description: com.bloomfilter.config
 * @Version: 1.0.0
 */
@Configuration
public class RedisConfig {

    private static final long expectedInsertions = 1000000;
    private static final double fpp = 0.00001;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setDefaultSerializer(StringRedisSerializer.UTF_8);
        return redisTemplate;
    }

    @Bean
    public BloomFilterHelper<CharSequence> bloomFilterHelper() {
        Funnel<CharSequence> funnel = Funnels.stringFunnel(CharsetUtil.UTF_8);
        return new BloomFilterHelper<>(funnel, expectedInsertions, fpp);
    }

}
