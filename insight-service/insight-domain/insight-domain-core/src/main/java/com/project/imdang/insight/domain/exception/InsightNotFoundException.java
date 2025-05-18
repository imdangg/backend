package com.project.imdang.insight.domain.exception;

import com.project.imdang.common.domain.exception.DomainNotFoundException;
import com.project.imdang.common.domain.valueobject.InsightId;

public class InsightNotFoundException extends DomainNotFoundException {

    public InsightNotFoundException(InsightId insightId) {
        this(String.format("Could not find insight[id: %s]!", insightId.getValue()));
    }

    private InsightNotFoundException(String message) {
        super(message);
    }

    private InsightNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
