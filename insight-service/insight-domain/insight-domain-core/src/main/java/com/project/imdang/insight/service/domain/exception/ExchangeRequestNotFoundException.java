package com.project.imdang.insight.service.domain.exception;

import com.project.imdang.domain.valueobject.ExchangeRequestId;

public class ExchangeRequestNotFoundException extends ExchangeDomainException {

    public ExchangeRequestNotFoundException(ExchangeRequestId exchangeRequestId) {
        this(String.format("Could not find ExchangeRequest[id: %s]!", exchangeRequestId.getValue()));
    }

    private ExchangeRequestNotFoundException(String message) {
        super(message);
    }

    private ExchangeRequestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
