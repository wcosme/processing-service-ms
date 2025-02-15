package br.com.poc.processingservice.shared.exception;


public record ErrorResponse(
        int status,
        String error,
        String message,
        String path,
        String timestamp
) {}

