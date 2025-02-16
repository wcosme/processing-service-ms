package br.com.poc.processingservice.infrastructure.persistence;

import br.com.poc.processingservice.application.domain.Transaction;
import br.com.poc.processingservice.application.port.output.TransactionRepositoryPortOut;
import br.com.poc.processingservice.infrastructure.persistence.entity.TransactionEntity;
import br.com.poc.processingservice.infrastructure.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionPersistence implements TransactionRepositoryPortOut {

    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public Mono<Void> save(Transaction transaction) {
        TransactionEntity transactionEntity = modelMapper.map(transaction, TransactionEntity.class);
        log.info("Persistindo transação no banco de forma assíncrona: {}", transactionEntity);
        return transactionRepository.save(transactionEntity).then();
    }
}