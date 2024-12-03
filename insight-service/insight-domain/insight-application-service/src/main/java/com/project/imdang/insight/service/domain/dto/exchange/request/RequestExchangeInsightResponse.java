package com.project.imdang.insight.service.domain.dto.exchange.request;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RequestExchangeInsightResponse {
    //교환 ID
    private final UUID exchangeId;

    public RequestExchangeInsightResponse(UUID exchangeId) {
        this.exchangeId = exchangeId;
    }
}
