package com.project.imdang.common.application.exception;

public class ExternalApiException extends RuntimeException {
    private final Integer code;

    public ExternalApiException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
