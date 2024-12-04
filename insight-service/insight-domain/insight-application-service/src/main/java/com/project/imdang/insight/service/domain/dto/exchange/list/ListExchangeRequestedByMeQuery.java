package com.project.imdang.insight.service.domain.dto.exchange.list;

import lombok.Getter;

import java.util.UUID;


@Getter
public class ListExchangeRequestedByMeQuery {
    private UUID requestMemberId;
    // 대기, 거절, 완료
}
