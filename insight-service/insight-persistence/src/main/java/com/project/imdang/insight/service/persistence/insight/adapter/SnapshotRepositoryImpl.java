package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
import com.project.imdang.insight.service.persistence.insight.entity.SnapshotEntity;
import com.project.imdang.insight.service.persistence.insight.mapper.SnapshotPersistenceMapper;
import com.project.imdang.insight.service.persistence.insight.repository.SnapshotJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SnapshotRepositoryImpl implements SnapshotRepository {

    private final SnapshotJpaRepository snapshotJpaRepository;
    private final SnapshotPersistenceMapper snapshotPersistenceMapper;

    @Override
    public Snapshot save(Snapshot snapshot) {
        SnapshotEntity snapshotEntity = snapshotPersistenceMapper.snapshotToSnapshotEntity(snapshot);
        SnapshotEntity saved = snapshotJpaRepository.save(snapshotEntity);
        return snapshotPersistenceMapper.snapshotEntityToSnapshot(saved);
    }
}
