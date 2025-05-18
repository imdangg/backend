package com.project.imdang.insight.domain.exception;

import com.project.imdang.common.domain.exception.DomainException;

public class InsightDomainException extends DomainException {

    public InsightDomainException(String message) {
        super(message);
    }

    public InsightDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
