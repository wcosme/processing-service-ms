package br.com.poc.processingservice.shared.constant;

import org.springframework.http.HttpStatus;

public class ErrorProperties {

    private final HttpStatus httpStatus;
    private final String errorName;
    private final String message;

    private ErrorProperties(HttpStatus httpStatus, String errorName, String message) {
        this.httpStatus = httpStatus;
        this.errorName = errorName;
        this.message = message;
    }

    public static ErrorProperties of(HttpStatus httpStatus, String errorName, String message) {
        return new ErrorProperties(httpStatus, errorName, message);
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getErrorName() {
        return errorName;
    }

    public String getMessage() {
        return message;
    }
}
