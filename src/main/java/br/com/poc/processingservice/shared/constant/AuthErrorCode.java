package br.com.poc.processingservice.shared.constant;

import org.springframework.http.HttpStatus;

public enum AuthErrorCode implements ErrorCode {

    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "Credenciais inválidas. Verifique o nome de usuário e a senha."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "Usuário não encontrado.");

    private final ErrorProperties errorProperties;

    AuthErrorCode(HttpStatus httpStatus, String message) {
        this.errorProperties = ErrorProperties.of(httpStatus, this.name(), message);
    }

    @Override
    public ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }
}