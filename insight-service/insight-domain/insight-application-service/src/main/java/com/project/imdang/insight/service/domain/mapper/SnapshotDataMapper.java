package com.project.imdang.insight.service.domain.mapper;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightSimpleResponse;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
import org.springframework.stereotype.Component;

@Component
public class SnapshotDataMapper {

    public InsightResponse snapshotToInsightResponse(Snapshot snapshot, String memberNickname, Integer recommendedCount) {
        return InsightResponse.builder()
                .insightId(snapshot.getInsightId().getValue())
                .recommendedCount(recommendedCount)
                .address(snapshot.getAddress())
                .title(snapshot.getTitle())
                .mainImage(snapshot.getMainImage())
                .memberNickname(memberNickname)
                .build();
    }

    public InsightSimpleResponse snapshotToInsightSimpleResponse(Snapshot snapshot, Integer recommendedCount) {
        return InsightSimpleResponse.builder()
                .insightId(snapshot.getInsightId().getValue())
                .recommendedCount(recommendedCount)
                .address(snapshot.getAddress())
                .title(snapshot.getTitle())
                .build();
    }

    public DetailInsightResponse snapshotToDetailInsightResponse(Snapshot snapshot,
                                                                 String memberNickname,
                                                                 Boolean recommended,
                                                                 Boolean accused,
                                                                 Integer recommendedCount,
                                                                 Integer accusedCount,
                                                                 Integer viewCount,
                                                                 ExchangeRequestStatus exchangeRequestStatus,
                                                                 Boolean exchangeRequestCreatedByMe,
                                                                 ExchangeRequestId exchangeRequestId) {
        return DetailInsightResponse.builder()
                .memberId(snapshot.getMemberId().getValue())
                .memberNickname(memberNickname)
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
                .recommended(recommended)
                .accused(accused)
                .recommendedCount(recommendedCount)
                .accusedCount(accusedCount)
                .viewCount(viewCount)
                .exchangeRequestStatus(exchangeRequestStatus)
                .exchangeRequestCreatedByMe(exchangeRequestCreatedByMe)
                .exchangeRequestId(exchangeRequestId != null ? exchangeRequestId.getValue() : null)
                .build();
    }
}
