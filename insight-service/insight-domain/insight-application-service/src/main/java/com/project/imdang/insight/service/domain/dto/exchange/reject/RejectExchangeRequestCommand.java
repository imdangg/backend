package com.project.imdang.insight.service.domain.dto.exchange.reject;

import lombok.Getter;

@Getter
public class RejectExchangeRequestCommand {
    private final String exchangeId;

    public RejectExchangeRequestCommand(String exchangeId) {
        this.exchangeId = exchangeId;
    }
}
