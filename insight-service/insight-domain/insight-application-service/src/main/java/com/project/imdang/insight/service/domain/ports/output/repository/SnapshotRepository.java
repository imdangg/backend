package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;

import java.util.Optional;

public interface SnapshotRepository {
    Snapshot save(Snapshot snapshot);
    Optional<Snapshot> findById(SnapshotId snapshotId);
}
