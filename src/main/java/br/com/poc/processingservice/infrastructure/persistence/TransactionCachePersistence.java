package br.com.poc.processingservice.infrastructure.persistence;

import br.com.poc.processingservice.application.port.input.dto.response.TransactionResponseDTO;
import br.com.poc.processingservice.application.port.output.TransactionCacheRepositoryPortOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;


@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionCachePersistence implements TransactionCacheRepositoryPortOut {

    private static final Duration CACHE_TTL = Duration.ofMinutes(10); // Expiração do cache (10 min)
    private final ReactiveRedisTemplate<String, TransactionResponseDTO> redisTemplate;

    @Override
    public Mono<Void> saveTransactionToCache(TransactionResponseDTO transaction) {
        String key = getTransactionCacheKey(transaction.getId());
        log.info("Salvando transação no cache: {}", transaction);
        return redisTemplate.opsForValue()
                .set(key, transaction, CACHE_TTL)
                .doOnSuccess(success -> log.info("Transação salva no cache com sucesso: {}", transaction.getId()))
                .then();
    }

    @Override
    public Mono<TransactionResponseDTO> getTransactionFromCache(UUID id) {
        String key = getTransactionCacheKey(id);
        log.info("Buscando transação no cache para o ID: {}", id);
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Mono<Void> removeTransactionFromCache(UUID id) {
        String key = getTransactionCacheKey(id);
        log.info("Removendo transação do cache para o ID: {}", id);
        return redisTemplate.opsForValue().delete(key).then();
    }

    private String getTransactionCacheKey(UUID id) {
        return "transaction:" + id.toString(); // Formato da chave no Redis
    }
}