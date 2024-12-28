package com.project.imdang.insight.service.domain.exception;

import com.project.imdang.domain.exception.DomainException;

public class SnapshotDomainException extends DomainException {

    public SnapshotDomainException(String message) {
        super(message);
    }

    public SnapshotDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
