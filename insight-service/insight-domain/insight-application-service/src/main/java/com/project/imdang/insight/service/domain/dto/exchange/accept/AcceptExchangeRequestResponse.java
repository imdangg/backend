package com.project.imdang.insight.service.domain.dto.exchange.accept;

import lombok.Getter;

@Getter
public class AcceptExchangeRequestResponse {
    private final String isAccept;

    public AcceptExchangeRequestResponse(String isAccept) {
        this.isAccept = isAccept;
    }
}
