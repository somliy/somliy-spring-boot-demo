package top.somliy.currentlimiting.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * 类名： @ClassName RedisConfig redis配置
 * 创建人：@author zhao dong
 * 类描述：@Description: redis配置
 * 创建时间: 2023/7/27 10:27
 */

@Configuration
public class RedisConfig {
    /**
     * 指定序列化方式 Jackson2JsonRedisSerializer
     *
     * @param factory redis
     * @return bean
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Serializable> getRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer() {
            @Override
            public byte[] serialize(String cacheKey) throws SerializationException {
                String key = "somliy:" + cacheKey;
                return key.getBytes(StandardCharsets.UTF_8);
            }
        };

        // key的序列化类型
        redisTemplate.setKeySerializer(stringRedisSerializer);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =
                new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // value的序列化类型
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
