package com.project.imdang.insight.service.domain.exception;

public class InsightNotFoundException extends InsightDomainException {

    public InsightNotFoundException(String message) {
        super(message);
    }

    public InsightNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
