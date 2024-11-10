package com.project.imdang.insight.service.domain.exception;

import com.project.imdang.domain.exception.DomainException;

public class InsightDomainException extends DomainException {

    public InsightDomainException(String message) {
        super(message);
    }

    public InsightDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
