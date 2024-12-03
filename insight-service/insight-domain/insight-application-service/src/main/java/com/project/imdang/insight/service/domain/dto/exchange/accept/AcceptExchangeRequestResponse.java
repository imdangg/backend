package com.project.imdang.insight.service.domain.dto.exchange.accept;

import lombok.Getter;

import java.util.UUID;

@Getter
public class AcceptExchangeRequestResponse {
    private final UUID exchangeRequestId;

    public AcceptExchangeRequestResponse(UUID exchangeRequestId) {
        this.exchangeRequestId = exchangeRequestId;
    }
}
