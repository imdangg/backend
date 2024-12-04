package com.project.imdang.insight.service.domain.dto.exchange.list;

import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
import lombok.Getter;

import java.util.UUID;


@Getter
public class ListExchangeRequestedByMeQuery {
    private UUID requestMemberId;
    // 대기, 거절, 완료
    private ExchangeRequestStatus exchangeRequestStatus;
}
