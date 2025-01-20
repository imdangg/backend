package com.project.imdang.insight.service.domain.mapper;

import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightSimpleResponse;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import org.springframework.stereotype.Component;

@Component
public class SnapshotDataMapper {

    public InsightResponse snapshotToInsightResponse(Snapshot snapshot) {
        return InsightResponse.builder()
                .insightId(snapshot.getInsightId().getValue())
                .recommendedCount(null)
                .address(snapshot.getAddress())
                .title(snapshot.getTitle())
                .mainImage(snapshot.getMainImage())
                .memberId(snapshot.getMemberId().getValue())
                .build();
    }

    public InsightSimpleResponse snapshotToInsightSimpleResponse(Snapshot snapshot) {
        return InsightSimpleResponse.builder()
                .insightId(snapshot.getInsightId().getValue())
                .recommendedCount(null)
                .address(snapshot.getAddress())
                .title(snapshot.getTitle())
                .build();
    }

    public DetailInsightResponse snapshotToDetailInsightResponse(Snapshot snapshot, ExchangeRequest exchangeRequest) {
        return DetailInsightResponse.builder()
                .memberId(snapshot.getMemberId().getValue())
                .insightId(snapshot.getInsightId().getValue())
                .snapshotId(snapshot.getId().getValue())
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
                .recommendedCount(null)
                .accusedCount(null)
                .viewCount(null)
                // TODO - CHECK
                .exchangeRequestStatus(exchangeRequest != null ? exchangeRequest.getStatus() : null)
                .exchangeRequestId(exchangeRequest != null ? exchangeRequest.getId().getValue() : null)
                .build();
    }
}
