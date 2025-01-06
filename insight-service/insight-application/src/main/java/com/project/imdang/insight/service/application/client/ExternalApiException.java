package com.project.imdang.insight.service.application.client;

public class ExternalApiException extends RuntimeException {
    private final Integer code;

    public ExternalApiException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
