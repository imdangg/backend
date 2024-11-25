package com.project.imdang.insight.service.domain.dto.exchange.request;

import lombok.Getter;

@Getter
public class RequestExchangeInsightResponse {
    //교환 ID
    private final String exchangeId;

    public RequestExchangeInsightResponse(String exchangeId) {
        this.exchangeId = exchangeId;
    }
}
