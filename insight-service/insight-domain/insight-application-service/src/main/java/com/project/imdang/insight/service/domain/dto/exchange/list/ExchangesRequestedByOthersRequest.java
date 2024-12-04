package com.project.imdang.insight.service.domain.dto.exchange.list;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ExchangesRequestedByOthersRequest {
    private final UUID memberId;

    public ExchangesRequestedByOthersRequest(UUID memberId) {
        this.memberId = memberId;
    }
}
