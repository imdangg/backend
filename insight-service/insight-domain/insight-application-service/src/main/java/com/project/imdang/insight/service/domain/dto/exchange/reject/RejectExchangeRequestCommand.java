package com.project.imdang.insight.service.domain.dto.exchange.reject;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RejectExchangeRequestCommand {
    private UUID exchangeId;

    public RejectExchangeRequestCommand(UUID exchangeId) {
        this.exchangeId = exchangeId;
    }
}
