package br.com.poc.processingservice.application.port.output;

import br.com.poc.processingservice.application.port.input.dto.response.TransactionResponseDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface TransactionCacheRepositoryPortOut {

    Mono<Void> saveTransactionToCache(TransactionResponseDTO transaction);
    Mono<TransactionResponseDTO> getTransactionFromCache(UUID id);
}
