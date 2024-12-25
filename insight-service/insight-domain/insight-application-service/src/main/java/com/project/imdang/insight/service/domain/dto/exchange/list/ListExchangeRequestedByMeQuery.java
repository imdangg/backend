package com.project.imdang.insight.service.domain.dto.exchange.list;

import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
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
public class ListExchangeRequestedByMeQuery {
    // TODO - PagingQuery 클래스 이동
//        extends PagingQuery {

    @Setter
    private UUID requestMemberId;
    // 대기, 거절, 완료
    private ExchangeRequestStatus exchangeRequestStatus;

    private Integer pageNumber;
    private Integer pageSize;
    private String direction;
    private String[] properties;
}
