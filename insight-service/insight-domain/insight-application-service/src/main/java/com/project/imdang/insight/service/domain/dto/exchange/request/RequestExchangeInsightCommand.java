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
public class RequestExchangeInsightCommand {

    private UUID requestedInsightId;

    // requestedBy
    private UUID requestMemberId;

    // 내가 작성한 인사이트
    private UUID requestMemberInsightId;
}
