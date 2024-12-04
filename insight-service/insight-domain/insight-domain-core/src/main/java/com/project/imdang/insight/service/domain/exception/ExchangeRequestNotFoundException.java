package com.project.imdang.insight.service.domain.exception;

public class ExchangeRequestNotFoundException extends ExchangeDomainException {

    public ExchangeRequestNotFoundException() {
        this("Cannot find ExchangeRequest.");
    }

    private ExchangeRequestNotFoundException(String message) {
        super(message);
    }

    private ExchangeRequestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
