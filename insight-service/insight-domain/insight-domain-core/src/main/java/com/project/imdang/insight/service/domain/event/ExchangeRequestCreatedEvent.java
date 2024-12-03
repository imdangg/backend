package com.project.imdang.insight.service.domain.event;

import java.util.UUID;

public class ExchangeRequestCreatedEvent {
    private final UUID InsightId;
    private final UUID RequestMemberId;

    public ExchangeRequestCreatedEvent(UUID insightId, UUID requestMemberId) {
        InsightId = insightId;
        RequestMemberId = requestMemberId;
    }
}
