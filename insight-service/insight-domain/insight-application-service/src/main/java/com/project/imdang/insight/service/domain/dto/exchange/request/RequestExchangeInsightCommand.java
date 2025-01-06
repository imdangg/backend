package com.project.imdang.insight.service.domain.dto.exchange.request;

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
public class RequestExchangeInsightCommand {
    @Schema(description = "요청 받은 인사이트ID")
    private UUID requestedInsightId;

    // requestedBy
    @Setter
    @Schema(description = "교환 요청한 사용자 ID")
    private UUID requestMemberId;

    /////////////////////////////////////////////////
    // 내가 작성한 인사이트
    @Schema(description = "교환 요청한 사용자의 인사이트ID")
    private UUID requestMemberInsightId;
    // OR
    @Schema(description = "쿠폰ID")
    private Long memberCouponId;
    /////////////////////////////////////////////////
}
