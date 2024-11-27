package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.insight.service.domain.entity.Snapshot;

public interface SnapshotRepository {
    Snapshot save(Snapshot snapshot);
}
