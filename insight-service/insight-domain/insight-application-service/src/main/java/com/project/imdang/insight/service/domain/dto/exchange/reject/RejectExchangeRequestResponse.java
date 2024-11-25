package com.project.imdang.insight.service.domain.dto.exchange.reject;

import lombok.Getter;

@Getter
public class RejectExchangeRequestResponse {
    private final String isRejected;

    public RejectExchangeRequestResponse(String isRejected) {
        this.isRejected = isRejected;
    }
}
