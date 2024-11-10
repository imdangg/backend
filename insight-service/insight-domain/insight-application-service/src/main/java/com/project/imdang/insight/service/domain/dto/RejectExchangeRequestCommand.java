package com.project.imdang.insight.service.domain.dto;

import lombok.Getter;

@Getter
public class RejectExchangeRequestCommand {
    private final String exchangeId;

    public RejectExchangeRequestCommand(String exchangeId) {
        this.exchangeId = exchangeId;
    }
}
