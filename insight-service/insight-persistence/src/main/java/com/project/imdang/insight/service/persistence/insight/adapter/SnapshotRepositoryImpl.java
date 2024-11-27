package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
import com.project.imdang.insight.service.persistence.insight.repository.SnapshotJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SnapshotRepositoryImpl implements SnapshotRepository {

    private final SnapshotJpaRepository snapshotJpaRepository;

    @Override
    public Snapshot save(Snapshot snapshot) {
        return null;
    }
}
