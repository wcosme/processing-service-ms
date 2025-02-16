package br.com.poc.processingservice.infrastructure.repository;

import br.com.poc.processingservice.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<TransactionEntity, UUID> {
    Mono<TransactionEntity> findById(UUID id);
}