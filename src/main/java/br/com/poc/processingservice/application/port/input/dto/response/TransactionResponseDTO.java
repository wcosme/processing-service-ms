package br.com.poc.processingservice.application.port.input.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {
    private UUID id;
    private UUID userId;
    private UUID categoryId;
    private String categoryName;
    private BigDecimal amount;
    private LocalDateTime date;
    private String status;
}
