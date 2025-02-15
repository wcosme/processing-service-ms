package br.com.poc.processingservice.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionProcess {
    private UUID id;
    private UUID userId;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
}