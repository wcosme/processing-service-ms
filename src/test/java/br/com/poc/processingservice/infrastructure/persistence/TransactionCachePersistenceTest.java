package br.com.poc.processingservice.infrastructure.persistence;

import br.com.poc.processingservice.application.port.input.dto.response.TransactionResponseDTO;
import br.com.poc.processingservice.application.port.output.TransactionCacheRepositoryPortOut;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


class TransactionCachePersistenceTest {

    @Mock
    private ReactiveRedisTemplate<String, TransactionResponseDTO> redisTemplate;

    @Mock
    private ValueOperations<String, TransactionResponseDTO> valueOperations;

    @InjectMocks
    private TransactionCacheRepositoryPortOut cacheRepository;

    private TransactionResponseDTO transaction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transaction = new TransactionResponseDTO(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                "Alimentação",
                BigDecimal.valueOf(50.75),
                LocalDateTime.now(),
                "PENDING"
        );
    }

}