package br.com.poc.processingservice.application.port.input.usecase;

import br.com.poc.processingservice.application.port.input.dto.request.TransactionRequestDTO;
import br.com.poc.processingservice.application.port.input.dto.response.TransactionResponseDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface TransactionUseCase {
    Mono<TransactionResponseDTO> processTransaction(TransactionRequestDTO request);
    Mono<TransactionResponseDTO> getTransactionById(UUID id);
}