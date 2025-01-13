package com.project.imdang.insight.service.domain.mapper;

import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import org.springframework.stereotype.Component;

@Component
public class SnapshotDataMapper {

    public InsightResponse snapshotToInsightResponse(Snapshot snapshot) {
        return InsightResponse.builder()
                .insightId(snapshot.getInsightId().getValue())
                .address(snapshot.getAddress())
                .title(snapshot.getTitle())
                .mainImage(snapshot.getMainImage())
                .memberId(snapshot.getMemberId().getValue())
                .build();
    }

    public DetailInsightResponse snapshotToDetailInsightResponse(Snapshot snapshot) {
        return DetailInsightResponse.builder()
                .snapshotId(snapshot.getId().getValue())
                .memberId(snapshot.getMemberId().getValue())
                .insightId(snapshot.getInsightId().getValue())
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
}
