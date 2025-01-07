package com.project.imdang.insight.service.domain.dto.exchange.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "교환 요청 ID")
    private UUID exchangeRequestId;
}
