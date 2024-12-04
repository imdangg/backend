package com.project.imdang.insight.service.domain.dto.exchange.list;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ExchangesRequestedByMeRequest {
    private final UUID memberId;

    public ExchangesRequestedByMeRequest(UUID memberId) {
        this.memberId = memberId;
    }
}
