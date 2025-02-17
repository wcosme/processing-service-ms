package br.com.poc.processingservice.shared.config;

import br.com.poc.processingservice.application.port.input.dto.response.TransactionResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.*;

@Configuration
public class RedisConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Suporte para LocalDateTime
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Formato ISO-8601
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // Evita valores nulos
        return objectMapper;
    }

    @Bean
    public ReactiveRedisTemplate<String, TransactionResponseDTO> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory factory, ObjectMapper objectMapper) {

        // 🔹 Criamos o serializador com o ObjectMapper diretamente no construtor
        Jackson2JsonRedisSerializer<TransactionResponseDTO> jsonSerializer =
                new Jackson2JsonRedisSerializer<>(objectMapper, TransactionResponseDTO.class);

        // 🔹 Serializador de chave em String
        RedisSerializationContext.RedisSerializationContextBuilder<String, TransactionResponseDTO> builder =
                RedisSerializationContext.newSerializationContext(StringRedisSerializer.UTF_8);

        // 🔹 Definição dos Serializadores
        RedisSerializationContext<String, TransactionResponseDTO> context = builder
                .key(StringRedisSerializer.UTF_8)  // Serializador para a chave
                .value(jsonSerializer)             // Serializador para o valor (objeto)
                .hashKey(StringRedisSerializer.UTF_8)  // Serializador para chave em hash
                .hashValue(jsonSerializer)         // Serializador para valor em hash
                .build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}