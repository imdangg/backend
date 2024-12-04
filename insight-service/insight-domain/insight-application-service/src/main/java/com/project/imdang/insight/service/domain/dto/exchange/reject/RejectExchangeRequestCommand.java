package com.project.imdang.insight.service.domain.dto.exchange.reject;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RejectExchangeRequestCommand {
  
    private final UUID exchangeRequestId;

    public RejectExchangeRequestCommand(UUID exchangeRequestId) {
        this.exchangeRequestId = exchangeRequestId;
    }
}
