package com.project.imdang.insight.service.domain.exception;

import com.project.imdang.domain.exception.DomainException;

public class ExchangeDomainException extends DomainException {

    public ExchangeDomainException(String message) {
        super(message);
    }

    public ExchangeDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
