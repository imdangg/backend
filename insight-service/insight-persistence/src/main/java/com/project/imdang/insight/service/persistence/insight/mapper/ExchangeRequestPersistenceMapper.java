package com.project.imdang.insight.service.persistence.insight.mapper;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
import com.project.imdang.insight.service.persistence.insight.entity.ExchangeRequestEntity;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRequestPersistenceMapper {

    public ExchangeRequestEntity exchangeRequestToExchangeRequestEntity(ExchangeRequest exchangeRequest) {
        return ExchangeRequestEntity.builder()
                .id(exchangeRequest.getId().getValue())
                .requestMemberId(exchangeRequest.getRequestMemberId().getValue())
                .requestMemberInsightId(
                        exchangeRequest.getRequestMemberInsightId() != null ?
                                exchangeRequest.getRequestMemberInsightId().getValue() : null)
                .requestMemberSnapshotId(
                        exchangeRequest.getRequestMemberSnapshotId() != null ?
                                exchangeRequest.getRequestMemberSnapshotId().getValue() : null)
                .memberCouponId(
                        exchangeRequest.getMemberCouponId() != null ?
                                exchangeRequest.getMemberCouponId().getValue() : null)
                .requestedInsightId(exchangeRequest.getRequestedInsightId().getValue())
                .requestedSnapshotId(exchangeRequest.getRequestedSnapshotId().getValue())
                .requestedMemberId(exchangeRequest.getRequestedMemberId().getValue())
                .requestedAt(exchangeRequest.getRequestedAt())
                .respondedAt(exchangeRequest.getRespondedAt())
                .status(exchangeRequest.getStatus())
                .build();
    }

    public ExchangeRequest exchangeRequestEntityToExchangeRequest(ExchangeRequestEntity exchangeRequestEntity) {
        return ExchangeRequest.builder()
                .id(new ExchangeRequestId(exchangeRequestEntity.getId()))
                .requestMemberId(new MemberId(exchangeRequestEntity.getRequestMemberId()))
                .requestMemberInsightId(
                        exchangeRequestEntity.getRequestMemberInsightId() != null ?
                                new InsightId(exchangeRequestEntity.getRequestMemberInsightId()) : null)
                .requestMemberSnapshotId(
                        exchangeRequestEntity.getRequestMemberSnapshotId() != null ?
                                new SnapshotId(exchangeRequestEntity.getRequestMemberSnapshotId()) : null)
                .memberCouponId(
                        exchangeRequestEntity.getMemberCouponId() != null ?
                                new MemberCouponId(exchangeRequestEntity.getMemberCouponId()) : null)
                .requestedInsightId(new InsightId(exchangeRequestEntity.getRequestedInsightId()))
                .requestedSnapshotId(new SnapshotId(exchangeRequestEntity.getRequestedSnapshotId()))
                .requestedMemberId(new MemberId(exchangeRequestEntity.getRequestedMemberId()))
                .requestedAt(exchangeRequestEntity.getRequestedAt())
                .respondedAt(exchangeRequestEntity.getRespondedAt())
                .status(exchangeRequestEntity.getStatus())
                .build();
    }
}
