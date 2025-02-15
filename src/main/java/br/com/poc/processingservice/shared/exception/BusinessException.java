package br.com.poc.processingservice.shared.exception;


import br.com.poc.processingservice.shared.constant.ErrorProperties;

public class BusinessException extends RuntimeException {

    private final String errorName;

    public BusinessException(ErrorProperties errorProperties) {
        super(errorProperties.getMessage());
        this.errorName = errorProperties.getErrorName();
    }

    public String getErrorName() {
        return errorName;
    }
}
