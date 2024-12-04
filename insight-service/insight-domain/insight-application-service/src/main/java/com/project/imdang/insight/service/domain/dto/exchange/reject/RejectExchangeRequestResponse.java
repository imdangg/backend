package com.project.imdang.insight.service.domain.dto.exchange.reject;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RejectExchangeRequestResponse {
    private final UUID insightId;

    public RejectExchangeRequestResponse(UUID insightId) {
        this.insightId = insightId;
    }
}
