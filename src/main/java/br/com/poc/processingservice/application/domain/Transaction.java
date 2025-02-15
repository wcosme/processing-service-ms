package br.com.poc.processingservice.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private UUID id;
    private UUID userId;
    private UUID categoryId;
    private String categoryName;
    private BigDecimal amount;
    private LocalDateTime date;
    private String status;
}