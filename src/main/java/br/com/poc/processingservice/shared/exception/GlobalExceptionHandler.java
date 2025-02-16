package br.com.poc.processingservice.shared.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final DateTimeFormatter TIMESTAMP_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @ExceptionHandler(BusinessException.class)
    public Mono<ErrorResponse> handleBusinessException(BusinessException ex, ServerWebExchange exchange) {
        return Mono.just(createErrorResponse(HttpStatus.BAD_REQUEST, "Bad Request", ex.getMessage(), exchange));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public Mono<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, ServerWebExchange exchange) {
        return Mono.just(createErrorResponse(HttpStatus.NOT_FOUND, "Not Found", ex.getMessage(), exchange));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Mono<ErrorResponse> handleValidationException(ConstraintViolationException ex, ServerWebExchange exchange) {
        return Mono.just(createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error", ex.getMessage(), exchange));
    }

    private ErrorResponse createErrorResponse(HttpStatus status, String error, String message, ServerWebExchange exchange) {
        return new ErrorResponse(
                status.value(),
                error,
                message,
                getCurrentRequestPath(exchange),
                getCurrentTimestamp()
        );
    }

    private String getCurrentTimestamp() {
        return LocalDateTime.now(ZoneOffset.UTC).format(TIMESTAMP_FORMATTER);
    }

    private String getCurrentRequestPath(ServerWebExchange exchange) {
        if (exchange != null && exchange.getRequest() != null) {
            return exchange.getRequest().getPath().toString();
        }
        return "N/A";
    }
}