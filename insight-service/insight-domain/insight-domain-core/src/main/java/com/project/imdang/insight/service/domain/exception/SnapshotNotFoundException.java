package com.project.imdang.insight.service.domain.exception;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;

public class SnapshotNotFoundException extends SnapshotDomainException {

    public SnapshotNotFoundException(SnapshotId snapshotId) {
        this(String.format("Could not find snapshot[id: %s]!", snapshotId.getValue()));
    }

    public SnapshotNotFoundException(InsightId insightId) {
        this(String.format("Could not find snapshot of insight[id: %s]!", insightId.getValue()));
    }

    private SnapshotNotFoundException(String message) {
        super(message);
    }

    private SnapshotNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
