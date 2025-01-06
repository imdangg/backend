package com.project.imdang.insight.service.domain.dto.exchange.request;

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

    private UUID requestedInsightId;

    // requestedBy
    @Setter
    private UUID requestMemberId;

    /////////////////////////////////////////////////
    // 내가 작성한 인사이트
    private UUID requestMemberInsightId;
    // OR
    private Long memberCouponId;
    /////////////////////////////////////////////////
}
