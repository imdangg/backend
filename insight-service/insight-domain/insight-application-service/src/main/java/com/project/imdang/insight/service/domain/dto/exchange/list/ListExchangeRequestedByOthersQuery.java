package com.project.imdang.insight.service.domain.dto.exchange.list;

import com.project.imdang.domain.dto.PagingQuery;
import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
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
public class ListExchangeRequestedByOthersQuery extends PagingQuery {
    private UUID requestedMemberId;
    // 대기, 거절, 완료
    private ExchangeRequestStatus exchangeRequestStatus;
}
