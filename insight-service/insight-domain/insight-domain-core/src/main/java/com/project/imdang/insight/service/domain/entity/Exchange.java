package com.project.imdang.insight.service.domain.entity;


import com.project.imdang.domain.entity.BaseEntity;
import com.project.imdang.domain.valueobject.ExchangeId;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.valueobject.ExchangeStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class Exchange extends BaseEntity<ExchangeId> {
    private final MemberId requestMemberId;
    private final InsightId requestMemberInsightId;

    private final InsightId requestedInsightId;

    private final ZonedDateTime requestedAt;
    private ZonedDateTime respondedAt;
    private ExchangeStatus status;

    @Builder
    public Exchange(MemberId requestMemberId, InsightId requestMemberInsightId, InsightId requestedInsightId, ZonedDateTime requestedAt, ZonedDateTime respondedAt, ExchangeStatus status) {
        this.requestMemberId = requestMemberId;
        this.requestMemberInsightId = requestMemberInsightId;
        this.requestedInsightId = requestedInsightId;
        this.requestedAt = requestedAt;
        this.respondedAt = respondedAt;
        this.status = status;
    }

    private Exchange(MemberId requestMemberId, InsightId requestMemberInsightId, InsightId requestedInsightId) {
        this.requestMemberId = requestMemberId;
        this.requestMemberInsightId = requestMemberInsightId;
        this.requestedInsightId = requestedInsightId;

        this.requestedAt = ZonedDateTime.now();
        this.status = ExchangeStatus.PENDING;
    }

    // TODO - CHECK : vs Builder
//    public static Exchange createNewExchange(MemberId requestMemberId, InsightId requestMemberInsightId, InsightId requestedInsightId) {
//        // TODO - 동일 글 중복 교환 요청 불가 (1개 글에 요청 1번으로 제한)
//        // requestMemberId - requestedInsightId UNIQUE
//        return new Exchange(requestMemberId, requestMemberInsightId, requestedInsightId);
//    }

    // request
//    public void initiateExchange() {
//        this.status = ExchangeStatus.PENDING;
//    }

    public void accept() {
        this.status = ExchangeStatus.ACCEPTED;
        this.respondedAt = ZonedDateTime.now();
    }

    public void reject() {
        this.status = ExchangeStatus.REJECTED;
        this.respondedAt = ZonedDateTime.now();
    }
}
