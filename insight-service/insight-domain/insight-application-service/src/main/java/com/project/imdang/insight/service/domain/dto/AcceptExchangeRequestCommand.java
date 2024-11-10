package com.project.imdang.insight.service.domain.dto;

import lombok.Getter;

@Getter
public class AcceptExchangeRequestCommand {
    private final String exchangeId;

    public AcceptExchangeRequestCommand(String exchangeId) {
        this.exchangeId = exchangeId;
    }
}
