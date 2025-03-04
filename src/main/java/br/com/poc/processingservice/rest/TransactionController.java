package br.com.poc.processingservice.rest;

import br.com.poc.processingservice.application.port.input.dto.request.TransactionRequestDTO;
import br.com.poc.processingservice.application.port.input.dto.response.TransactionResponseDTO;
import br.com.poc.processingservice.shared.exception.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;
import reactor.core.publisher.Flux;

@Tag(name = "Transaction Controller", description = "Gerencia as transações dos usuários")
@RequestMapping(value = "/processing/api/v1/transactions")
public interface TransactionController {

    @Operation(summary = "Envia uma requisição de análise de transação", tags = "transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Transação em processamento"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.class)))
    })
    @PostMapping
    Mono<ResponseEntity<TransactionResponseDTO>> processTransaction(@RequestBody @Valid TransactionRequestDTO request);

    @Operation(summary = "Consulta o status de uma transação", tags = "transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status da transação retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Transação não encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.class)))
    })
    @GetMapping("/{id}")
    Mono<ResponseEntity<TransactionResponseDTO>> getTransactionStatus(@PathVariable UUID id);

    @Operation(summary = "Consulta as transações de um usuário", tags = "transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de transações retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma transação encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.class)))
    })
    @GetMapping("/user/{userId}")
    Flux<TransactionResponseDTO> getUserTransactions(@PathVariable UUID userId);
}