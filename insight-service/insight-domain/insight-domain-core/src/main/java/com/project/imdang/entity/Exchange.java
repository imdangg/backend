package com.project.imdang.entity;


import com.project.imdang.valueobject.ExchangeId;
import com.project.imdang.valueobject.ExchangeStatus;
import com.project.imdang.valueobject.InsightId;
import com.project.imdang.valueobject.MemberId;

import java.time.ZonedDateTime;

public class Exchange {
    private ExchangeId id;
    private final MemberId requestMemberId;
    private final InsightId requestMemberInsightId;

    private final InsightId requestedInsightId;

    private ZonedDateTime exchangeRequestedAt;
    private ExchangeStatus status;
    private ZonedDateTime exchangeRespondedAt;

    private Exchange(MemberId requestMemberId, InsightId requestMemberInsightId, InsightId requestedInsightId) {
        this.requestMemberId = requestMemberId;
        this.requestMemberInsightId = requestMemberInsightId;
        this.requestedInsightId = requestedInsightId;

        this.exchangeRequestedAt = ZonedDateTime.now();
        this.status = ExchangeStatus.PENDING;
    }

    // TODO - CHECK : vs Builder
    public static Exchange createNewExchange(MemberId requestMemberId, InsightId requestMemberInsightId, InsightId requestedInsightId) {
        // TODO - 동일 글 중복 교환 요청 불가 (1개 글에 요청 1번으로 제한)
        // requestMemberId - requestedInsightId UNIQUE
        return new Exchange(requestMemberId, requestMemberInsightId, requestedInsightId);
    }

    // request
//    public void initiateExchange() {
//        this.status = ExchangeStatus.PENDING;
//    }

    public void accept() {
        this.status = ExchangeStatus.ACCEPTED;
        this.exchangeRespondedAt = ZonedDateTime.now();
    }

    public void reject() {
        this.status = ExchangeStatus.REJECTED;
        this.exchangeRespondedAt = ZonedDateTime.now();
    }
}
