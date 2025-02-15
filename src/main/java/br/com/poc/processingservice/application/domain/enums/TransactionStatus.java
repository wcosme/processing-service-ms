package br.com.poc.processingservice.application.domain.enums;

public enum TransactionStatus {
    PENDING("Pending"),    // Transação aguardando processamento
    PROCESSED("Processed"),   // Transação concluída com sucesso
    FAILED("Failed");     // Transação falhou

    private final String description;

    TransactionStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

