package com.project.imdang.insight.service.domain.entity;


import com.project.imdang.domain.entity.BaseEntity;
import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
public class ExchangeRequest extends BaseEntity<ExchangeRequestId> {
    private final MemberId requestMemberId;
    private final InsightId requestMemberInsightId;

    private final InsightId requestedInsightId;
    private final MemberId requestedMemberId;

    private final ZonedDateTime requestedAt;
    private ZonedDateTime respondedAt;
    private ExchangeRequestStatus status;

    @Builder
    public ExchangeRequest(ExchangeRequestId id, MemberId requestMemberId, InsightId requestMemberInsightId, InsightId requestedInsightId, MemberId requestedMemberId, ZonedDateTime requestedAt, ZonedDateTime respondedAt, ExchangeRequestStatus status) {
        this.requestMemberId = requestMemberId;
        this.requestMemberInsightId = requestMemberInsightId;
        this.requestedInsightId = requestedInsightId;
        this.requestedMemberId = requestedMemberId;
        this.requestedAt = requestedAt;
        this.respondedAt = respondedAt;
        this.status = status;
    }

    public static ExchangeRequest createExchangeRequest(MemberId requestMemberId, InsightId requestMemberInsightId, InsightId requestedInsightId, MemberId requestedMemberId) {
        // TODO - 동일 글 중복 교환 요청 불가 (1개 글에 요청 1번으로 제한)
        // requestMemberId - requestedInsightId UNIQUE
        return ExchangeRequest.builder()
                .id(new ExchangeRequestId(UUID.randomUUID()))
                .requestMemberId(requestMemberId)
                .requestMemberInsightId(requestMemberInsightId)
                .requestedInsightId(requestedInsightId)
                .requestedMemberId(requestedMemberId)
                .requestedAt(ZonedDateTime.now())
                .status(ExchangeRequestStatus.PENDING)
                .build();
    }

    public void accept() {
        this.status = ExchangeRequestStatus.ACCEPTED;
        this.respondedAt = ZonedDateTime.now();
    }

    public void reject() {
        this.status = ExchangeRequestStatus.REJECTED;
        this.respondedAt = ZonedDateTime.now();
    }
}
