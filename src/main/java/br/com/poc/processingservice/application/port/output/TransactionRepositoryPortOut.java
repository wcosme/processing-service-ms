package br.com.poc.processingservice.application.port.output;

import br.com.poc.processingservice.application.domain.Transaction;
import reactor.core.publisher.Mono;

public interface TransactionRepositoryPortOut {
    Mono<Void> save(Transaction transaction);
}