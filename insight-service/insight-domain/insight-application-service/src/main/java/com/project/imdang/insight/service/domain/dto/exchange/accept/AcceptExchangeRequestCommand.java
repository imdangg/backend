package com.project.imdang.insight.service.domain.dto.exchange.accept;

import lombok.Getter;

import java.util.UUID;

@Getter
public class AcceptExchangeRequestCommand {
    private final UUID exchangeRequestId;

    public AcceptExchangeRequestCommand(UUID exchangeRequestId) {
        this.exchangeRequestId = exchangeRequestId;
    }
}
