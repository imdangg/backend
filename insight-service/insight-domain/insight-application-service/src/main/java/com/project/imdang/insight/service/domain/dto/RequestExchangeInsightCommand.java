package com.project.imdang.insight.service.domain.dto;

import lombok.Getter;

@Getter
public class RequestExchangeInsightCommand {

    // TODO - CHECK : originalId?
    private final String requestedInsightId;

    // requestedBy
    private final String requestMemberId;

    // 내가 작성한 인사이트
    private final String requestMemberInsightId;

    public RequestExchangeInsightCommand(String requestedInsightId, String requestMemberId, String requestMemberInsightId) {
        this.requestedInsightId = requestedInsightId;
        this.requestMemberId = requestMemberId;
        this.requestMemberInsightId = requestMemberInsightId;
    }
}
