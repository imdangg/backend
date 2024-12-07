package com.project.imdang.insight.service.domain.dto.exchange.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestExchangeInsightResponse {
    private UUID exchangeRequestId;
}
