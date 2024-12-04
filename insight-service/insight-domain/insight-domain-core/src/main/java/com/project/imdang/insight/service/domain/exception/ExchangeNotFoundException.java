package com.project.imdang.insight.service.domain.exception;

public class ExchangeNotFoundException extends ExchangeDomainException {

    public ExchangeNotFoundException(String message) {
        super(message);
    }

    public ExchangeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
