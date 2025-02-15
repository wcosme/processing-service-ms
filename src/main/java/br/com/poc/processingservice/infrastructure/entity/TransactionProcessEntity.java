package br.com.poc.processingservice.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "transaction_processes")
public class TransactionProcessEntity {

    @Id
    private UUID id;
    private UUID userId;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
}