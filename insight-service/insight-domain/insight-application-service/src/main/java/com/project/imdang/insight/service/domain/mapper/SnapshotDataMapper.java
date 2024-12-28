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
                .insightId(snapshot.getInsightId().getValue())
                .memberId(snapshot.getMemberId().getValue())
                .address(snapshot.getAddress())
                .title(snapshot.getTitle())
                .contents(snapshot.getContents())
                .mainImage(snapshot.getMainImage())
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
}
