package com.project.imdang.insight.service.domain.entity;


import com.project.imdang.domain.entity.AggregateRoot;
import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
public class ExchangeRequest extends AggregateRoot<ExchangeRequestId> {
    private final MemberId requestMemberId;
    private final InsightId requestMemberInsightId;

    private final InsightId requestedInsightId;
    private final MemberId requestedMemberId;

    private ZonedDateTime requestedAt;
    private ZonedDateTime respondedAt;
    private ExchangeRequestStatus status;

    @Builder
    public ExchangeRequest(ExchangeRequestId id, MemberId requestMemberId, InsightId requestMemberInsightId, InsightId requestedInsightId, MemberId requestedMemberId, ZonedDateTime requestedAt, ZonedDateTime respondedAt, ExchangeRequestStatus status) {
        setId(id);
        this.requestMemberId = requestMemberId;
        this.requestMemberInsightId = requestMemberInsightId;
        this.requestedInsightId = requestedInsightId;
        this.requestedMemberId = requestedMemberId;
        this.requestedAt = requestedAt;
        this.respondedAt = respondedAt;
        this.status = status;
    }

    public void initialize() {
        
        if (Objects.isNull(requestMemberId) || Objects.isNull(requestMemberInsightId)
                || Objects.isNull(requestedInsightId) || Objects.isNull(requestedMemberId)) {
            // TODO - 예외 처리
            throw new IllegalArgumentException();
        }

        // TODO - 동일 글 중복 교환 요청 불가 (1개 글에 요청 1번으로 제한)
        // requestMemberId - requestedInsightId UNIQUE
        ExchangeRequestId id = new ExchangeRequestId(UUID.randomUUID());
        setId(id);
        this.requestedAt = ZonedDateTime.now();
        this.status = ExchangeRequestStatus.PENDING;
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
