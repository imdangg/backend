package com.project.imdang.insight.service.domain.exception;

import com.project.imdang.insight.service.domain.valueobject.SnapshotId;

public class SnapshotNotFoundException extends SnapshotDomainException {

    public SnapshotNotFoundException(SnapshotId snapshotId) {
        this(String.format("Could not find snapshot[id: %s]", snapshotId.getValue()));
    }

    private SnapshotNotFoundException(String message) {
        super(message);
    }

    private SnapshotNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
