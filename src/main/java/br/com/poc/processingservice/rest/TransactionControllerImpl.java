package br.com.poc.processingservice.rest;

import br.com.poc.processingservice.application.port.input.dto.request.TransactionRequestDTO;
import br.com.poc.processingservice.application.port.input.dto.response.TransactionResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class TransactionControllerImpl implements TransactionController {


    @Override
    public ResponseEntity<Void> processTransaction(TransactionRequestDTO request) {
        return null;
    }

    @Override
    public ResponseEntity<TransactionResponseDTO> getTransactionStatus(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<List<TransactionResponseDTO>> getUserTransactions(UUID userId) {
        return null;
    }
}
