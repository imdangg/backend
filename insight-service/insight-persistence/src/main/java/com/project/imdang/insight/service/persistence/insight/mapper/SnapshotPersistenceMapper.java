package com.project.imdang.insight.service.persistence.insight.mapper;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
import com.project.imdang.insight.service.persistence.insight.entity.SnapshotEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SnapshotPersistenceMapper {

    public SnapshotEntity snapshotToSnapshotEntity(Snapshot snapshot) {
        return SnapshotEntity.builder()
                .id(!Objects.isNull(snapshot.getId()) ? snapshot.getId().getValue() : null)
                .insightId(snapshot.getInsightId().getValue())
                .memberId(snapshot.getMemberId().getValue())
                .mainImage(snapshot.getMainImage())
                .title(snapshot.getTitle())
                .address(snapshot.getAddress())
                .apartmentComplex(snapshot.getApartmentComplex())
                .visitAt(snapshot.getVisitAt())
                .visitTimes(snapshot.getVisitTimes())
                .visitMethods(snapshot.getVisitMethods())
                .access(snapshot.getAccess())
                .summary(snapshot.getSummary())
                .infra(snapshot.getInfra())
                .complexEnvironment(snapshot.getComplexEnvironment())
                .complexFacility(snapshot.getComplexFacility())
                .favorableNews(snapshot.getFavorableNews())
                .build();
    }

    public Snapshot snapshotEntityToSnapshot(SnapshotEntity snapshotEntity) {
        return Snapshot.builder()
                .id(new SnapshotId(snapshotEntity.getId()))
                .insightId(new InsightId(snapshotEntity.getInsightId()))
                .memberId(new MemberId(snapshotEntity.getMemberId()))
                .mainImage(snapshotEntity.getMainImage())
                .title(snapshotEntity.getTitle())
                .address(snapshotEntity.getAddress())
                .apartmentComplex(snapshotEntity.getApartmentComplex())
                .visitAt(snapshotEntity.getVisitAt())
                .visitTimes(snapshotEntity.getVisitTimes())
                .visitMethods(snapshotEntity.getVisitMethods())
                .access(snapshotEntity.getAccess())
                .summary(snapshotEntity.getSummary())
                .infra(snapshotEntity.getInfra())
                .complexEnvironment(snapshotEntity.getComplexEnvironment())
                .complexFacility(snapshotEntity.getComplexFacility())
                .favorableNews(snapshotEntity.getFavorableNews())
                .build();
    }
}
