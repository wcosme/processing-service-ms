package br.com.poc.processingservice.application.port.output;

import br.com.poc.processingservice.application.domain.Transaction;
import br.com.poc.processingservice.application.port.input.dto.response.TransactionResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface TransactionRepositoryPortOut {
    Mono<TransactionResponseDTO> save(Transaction transaction);
    Mono<TransactionResponseDTO> findById(UUID id);
    Flux<TransactionResponseDTO> findByUserId(UUID userId);
}