package br.com.poc.processingservice.application.service;

import br.com.poc.processingservice.application.domain.Transaction;
import br.com.poc.processingservice.application.domain.enums.TransactionStatus;
import br.com.poc.processingservice.application.port.input.dto.request.TransactionRequestDTO;
import br.com.poc.processingservice.application.port.input.dto.response.TransactionResponseDTO;
import br.com.poc.processingservice.application.port.input.usecase.TransactionUseCase;
import br.com.poc.processingservice.application.port.output.TransactionRepositoryPortOut;
import br.com.poc.processingservice.shared.constant.TransactionErrorCode;
import br.com.poc.processingservice.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService implements TransactionUseCase {

    private final TransactionRepositoryPortOut transactionRepositoryPortOut;

    @Override
    public Mono<TransactionResponseDTO> processTransaction(TransactionRequestDTO request) {
        Transaction transaction = Transaction.builder()
                .id(UUID.randomUUID()) // Gera ID único
                .userId(request.getTransaction().getUserId())
                .categoryId(request.getTransaction().getCategoryId())
                .categoryName(request.getTransaction().getCategoryName())
                .amount(request.getTransaction().getAmount())
                .date(LocalDateTime.now())
                .status(TransactionStatus.PENDING.name())
                .build();

        return transactionRepositoryPortOut.save(transaction)
                .thenReturn(new TransactionResponseDTO(transaction.getId(), transaction.getUserId(),
                        transaction.getCategoryId(), transaction.getCategoryName(),
                        transaction.getAmount(), transaction.getDate(), transaction.getStatus()));
    }

    @Override
    public Mono<TransactionResponseDTO> getTransactionById(UUID id) {
        return transactionRepositoryPortOut.findById(id)
                .switchIfEmpty(Mono.error(
                        new ResourceNotFoundException(
                                TransactionErrorCode.TRANSACTION_NOT_FOUND.getErrorProperties().getMessage())));
    }

    @Override
    public Flux<TransactionResponseDTO> getTransactionsByUserId(UUID userId) {
        return transactionRepositoryPortOut.findByUserId(userId);
    }
}