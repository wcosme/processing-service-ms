package br.com.poc.processingservice.application.port.input.usecase;

import br.com.poc.processingservice.application.port.input.dto.request.TransactionRequestDTO;
import br.com.poc.processingservice.application.port.input.dto.response.TransactionResponseDTO;
import reactor.core.publisher.Mono;

public interface TransactionUseCase {
    Mono<Void> processTransaction(TransactionRequestDTO request);
}