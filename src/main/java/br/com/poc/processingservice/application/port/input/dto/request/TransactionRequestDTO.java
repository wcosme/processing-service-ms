package br.com.poc.processingservice.application.port.input.dto.request;

import br.com.poc.processingservice.application.port.input.dto.TransactionDto;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequestDTO {

    @Valid
    private TransactionDto transaction;
}
