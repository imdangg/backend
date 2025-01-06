package com.project.imdang.insight.service.domain.dto.exchange.reject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RejectExchangeRequestCommand {
    @Schema(description = "교환 요청ID")
    private UUID exchangeRequestId;
    @Setter
    @Schema(description = "요청 받은 사용자ID")
    private UUID requestedMemberId;
}
