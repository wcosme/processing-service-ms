package br.com.poc.processingservice.infrastructure.persistence;

import br.com.poc.processingservice.application.domain.Transaction;
import br.com.poc.processingservice.application.port.input.dto.response.TransactionResponseDTO;
import br.com.poc.processingservice.application.port.output.TransactionCacheRepositoryPortOut;
import br.com.poc.processingservice.application.port.output.TransactionRepositoryPortOut;
import br.com.poc.processingservice.infrastructure.persistence.entity.TransactionEntity;
import br.com.poc.processingservice.infrastructure.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionPersistence implements TransactionRepositoryPortOut {

    private final TransactionRepository transactionRepository;
    private final TransactionCacheRepositoryPortOut cacheRepository;
    private final ModelMapper modelMapper;

    @Override
    public Mono<TransactionResponseDTO> save(Transaction transaction) {
        TransactionEntity transactionEntity = modelMapper.map(transaction, TransactionEntity.class);
        log.info("Persistindo transação no banco de forma assíncrona: {}", transactionEntity);

        return transactionRepository.save(transactionEntity)
                .map(entity -> modelMapper.map(entity, TransactionResponseDTO.class))
                .flatMap(transactionDTO -> cacheRepository
                        .saveTransactionToCache(transactionDTO)
                        .thenReturn(transactionDTO)); // Salva no Redis após persistência no MongoDB
    }

    @Override
    public Mono<TransactionResponseDTO> findById(UUID id) {
        return cacheRepository.getTransactionFromCache(id)
                .doOnNext(transaction -> log.info("✅ Transação encontrada no cache: {}", transaction))
                .switchIfEmpty(
                        transactionRepository.findById(id)
                                .map(entity -> modelMapper.map(entity, TransactionResponseDTO.class))
                                .flatMap(transactionDTO -> cacheRepository
                                        .saveTransactionToCache(transactionDTO)
                                        .thenReturn(transactionDTO))
                                .doOnNext(transaction -> log.info("🛢️ Transação buscada no banco e salva no cache: {}", transaction))
                );
    }

    @Override
    public Flux<TransactionResponseDTO> findByUserId(UUID userId) {
        return transactionRepository.findByUserId(userId)
                .map(entity -> modelMapper.map(entity, TransactionResponseDTO.class));
    }
}