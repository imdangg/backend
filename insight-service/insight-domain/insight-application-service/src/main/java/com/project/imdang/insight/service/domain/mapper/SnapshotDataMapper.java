package com.project.imdang.insight.service.domain.mapper;

import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightResponse;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import org.springframework.stereotype.Component;

@Component
public class SnapshotDataMapper {

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
