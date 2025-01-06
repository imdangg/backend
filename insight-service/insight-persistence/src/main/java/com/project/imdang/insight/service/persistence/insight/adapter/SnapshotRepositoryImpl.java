package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.domain.valueobject.BaseId;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
import com.project.imdang.insight.service.persistence.insight.entity.SnapshotEntity;
import com.project.imdang.insight.service.persistence.insight.mapper.SnapshotPersistenceMapper;
import com.project.imdang.insight.service.persistence.insight.repository.SnapshotJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Snapshot> findById(SnapshotId snapshotId) {
        return snapshotJpaRepository.findById(snapshotId.getValue())
                .map(snapshotPersistenceMapper::snapshotEntityToSnapshot);
    }

    @Override
    public Optional<Snapshot> findLatestByInsightId(InsightId insightId) {
        return snapshotJpaRepository.findTopByInsightIdEqualsOrderByIdDesc(insightId.getValue())
                .map(snapshotPersistenceMapper::snapshotEntityToSnapshot);
    }

    @Override
    public List<Snapshot> findAllByIds(List<SnapshotId> snapshotIds) {
        List<Long> ids = snapshotIds.stream().map(BaseId::getValue).toList();
        return snapshotJpaRepository.findAllById(ids).stream()
                .map(snapshotPersistenceMapper::snapshotEntityToSnapshot)
                .toList();
    }
}
