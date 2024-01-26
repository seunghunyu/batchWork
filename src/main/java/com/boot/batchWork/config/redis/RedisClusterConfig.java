package com.boot.batchWork.config.redis;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.util.List;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600) //1hour
public class RedisClusterConfig {

    @Value("${spring.redis_cluster.cluster.nodes}")
    private List<String> clusterNodes;

    @Primary
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(clusterNodes);
        return new LettuceConnectionFactory(redisClusterConfiguration);
    }

    @Primary
    @Bean
    public StringRedisTemplate  redisClusterTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }

}
