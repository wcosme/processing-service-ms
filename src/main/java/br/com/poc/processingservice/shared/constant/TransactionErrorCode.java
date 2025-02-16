package br.com.poc.processingservice.shared.constant;

import org.springframework.http.HttpStatus;

public enum TransactionErrorCode implements ErrorCode {

    TRANSACTION_NOT_FOUND(HttpStatus.NOT_FOUND, "Transação não encontrada para o ID:"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "Usuário não encontrado.");

    private final ErrorProperties errorProperties;

    TransactionErrorCode(HttpStatus httpStatus, String message) {
        this.errorProperties = ErrorProperties.of(httpStatus, this.name(), message);
    }

    @Override
    public ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }
}