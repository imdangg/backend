package com.project.imdang.insight.service.domain.dto.exchange.request;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RequestExchangeInsightCommand {

    // TODO - CHECK : originalId?
    private final UUID requestedInsightId;

    // requestedBy
    private final UUID requestMemberId;

    // 내가 작성한 인사이트
    private final UUID requestMemberInsightId;

    public RequestExchangeInsightCommand(UUID requestedInsightId, UUID requestMemberId, UUID requestMemberInsightId) {
        this.requestedInsightId = requestedInsightId;
        this.requestMemberId = requestMemberId;
        this.requestMemberInsightId = requestMemberInsightId;
    }
}
