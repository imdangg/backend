package com.project.imdang.insight.service.domain.dto;

import lombok.Getter;

@Getter
public class RequestInsightCommand {

    // TODO - CHECK : originalId?
    private final String requestedInsightId;

    // requestedBy
    private final String requestMemberId;

    private final String memberCouponId;

    public RequestInsightCommand(String requestedInsightId, String requestMemberId, String memberCouponId) {
        this.requestedInsightId = requestedInsightId;
        this.requestMemberId = requestMemberId;
        this.memberCouponId = memberCouponId;
    }
}
