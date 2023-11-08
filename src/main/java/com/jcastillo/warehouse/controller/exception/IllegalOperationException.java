package com.jcastillo.warehouse.controller.exception;

import org.springframework.http.HttpStatus;

public class IllegalOperationException extends RuntimeException {
    private HttpStatus status;

    public IllegalOperationException(String message) {
        super(message);
    }

    public IllegalOperationException(HttpStatus httpStatus, String message) {
        super(message);
        this.status = httpStatus;
    }

    public IllegalOperationException(HttpStatus httpStatus, Throwable cause) {
        super(cause);
        this.status = httpStatus;
    }

    public IllegalOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrorMessage() {
        return status.value() + ":".concat(getMessage());
    }

    public HttpStatus getStatus() {
        return status;
    }
}
