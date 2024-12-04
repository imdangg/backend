package com.project.imdang.insight.service.domain.exception;

import com.project.imdang.domain.exception.DomainException;

public class RequestDomainException extends DomainException {

    public RequestDomainException(String message) {
        super(message);
    }

    public RequestDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
