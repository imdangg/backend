package com.project.imdang.insight.service.persistence.insight.mapper;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
import com.project.imdang.insight.service.persistence.insight.entity.SnapshotEntity;
import org.springframework.stereotype.Component;

@Component
public class SnapshotPersistenceMapper {

    public SnapshotEntity snapshotToSnapshotEntity(Snapshot snapshot) {
        return SnapshotEntity.builder()
                .id(snapshot.getId().getValue())
                .memberId(snapshot.getMemberId().getValue())
                .address(snapshot.getAddress())
                .apartmentComplex(snapshot.getApartmentComplex())
                .title(snapshot.getTitle())
                .contents(snapshot.getContents())
                .summary(snapshot.getSummary())
                .visitAt(snapshot.getVisitAt())
                .visitMethod(snapshot.getVisitMethod())
                .access(snapshot.getAccess())
                .infra(snapshot.getInfra())
                .complexEnvironment(snapshot.getComplexEnvironment())
                .complexFacility(snapshot.getComplexFacility())
                .favorableNews(snapshot.getFavorableNews())
                .build();
    }

    public Snapshot snapshotEntityToSnapshot(SnapshotEntity snapshotEntity) {
        return Snapshot.builder()
                .id(new SnapshotId(snapshotEntity.getId()))
                .memberId(new MemberId(snapshotEntity.getMemberId()))
                .address(snapshotEntity.getAddress())
                .apartmentComplex(snapshotEntity.getApartmentComplex())
                .title(snapshotEntity.getTitle())
                .contents(snapshotEntity.getContents())
                .summary(snapshotEntity.getSummary())
                .visitAt(snapshotEntity.getVisitAt())
                .visitMethod(snapshotEntity.getVisitMethod())
                .access(snapshotEntity.getAccess())
                .infra(snapshotEntity.getInfra())
                .complexEnvironment(snapshotEntity.getComplexEnvironment())
                .complexFacility(snapshotEntity.getComplexFacility())
                .favorableNews(snapshotEntity.getFavorableNews())
                .build();
    }
}
