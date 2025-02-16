package br.com.poc.processingservice.rest;

import br.com.poc.processingservice.application.port.input.dto.request.TransactionRequestDTO;
import br.com.poc.processingservice.application.port.input.dto.response.TransactionResponseDTO;
import br.com.poc.processingservice.application.port.input.usecase.TransactionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class TransactionControllerImpl implements TransactionController {

    private final TransactionUseCase transactionUseCase;


    @Override
    public Mono<ResponseEntity<TransactionResponseDTO>> processTransaction(TransactionRequestDTO request) {
        return transactionUseCase.processTransaction(request)
                .map(transaction -> ResponseEntity.ok().body(transaction));
    }

    @Override
    public Mono<ResponseEntity<TransactionResponseDTO>> getTransactionStatus(UUID id) {
        return transactionUseCase.getTransactionById(id).map(ResponseEntity::ok);
    }

    @Override
    public Flux<ResponseEntity<TransactionResponseDTO>> getUserTransactions(UUID userId) {
        return null;
    }
}
