package com.project.imdang.insight.service.domain.exception;

import com.project.imdang.domain.valueobject.InsightId;

public class InsightNotFoundException extends InsightDomainException {

    public InsightNotFoundException(InsightId insightId) {
        super(String.format("Could not find insight[id: %s]", insightId.getValue()));
    }

    public InsightNotFoundException(String message) {
        super(message);
    }

    public InsightNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
