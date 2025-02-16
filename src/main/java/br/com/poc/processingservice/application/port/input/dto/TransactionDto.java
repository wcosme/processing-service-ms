package br.com.poc.processingservice.application.port.input.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TransactionDto {

    @NotNull(message = "O userId não pode ser nulo.")
    private UUID userId;

    @NotNull(message = "O categoryId não pode ser nulo.")
    private UUID categoryId;

    @NotBlank(message = "O categoryName não pode estar em branco.")
    @Size(min = 3, max = 50, message = "O categoryName deve ter entre 3 e 50 caracteres.")
    private String categoryName;

    @NotNull(message = "O amount não pode ser nulo.")
    @DecimalMin(value = "0.01", message = "O amount deve ser maior que zero.")
    private BigDecimal amount;

    private LocalDateTime date = LocalDateTime.now();
}