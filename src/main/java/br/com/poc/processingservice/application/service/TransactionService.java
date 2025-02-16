package br.com.poc.processingservice.application.service;

import br.com.poc.processingservice.application.domain.Transaction;
import br.com.poc.processingservice.application.domain.enums.TransactionStatus;
import br.com.poc.processingservice.application.port.input.dto.request.TransactionRequestDTO;
import br.com.poc.processingservice.application.port.input.usecase.TransactionUseCase;
import br.com.poc.processingservice.application.port.output.TransactionRepositoryPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService implements TransactionUseCase {

    private final TransactionRepositoryPortOut transactionRepositoryPortOut;

    @Override
    public Mono<Void> processTransaction(TransactionRequestDTO request) {
        Transaction transaction = Transaction.builder()
                .id(UUID.randomUUID()) // Gera ID único
                .userId(request.getTransaction().getUserId())
                .categoryId(request.getTransaction().getCategoryId())
                .categoryName(request.getTransaction().getCategoryName())
                .amount(request.getTransaction().getAmount())
                .date(request.getTransaction().getDate())
                .status(TransactionStatus.PENDING.name()) // Status inicial PENDING
                .build();

        return transactionRepositoryPortOut.save(transaction);
    }
}