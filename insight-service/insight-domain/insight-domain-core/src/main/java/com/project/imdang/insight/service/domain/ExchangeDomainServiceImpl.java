package com.project.imdang.insight.service.domain;

import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.event.ExchangeRequestAcceptedEvent;
import com.project.imdang.insight.service.domain.event.ExchangeRequestCreatedEvent;
import com.project.imdang.insight.service.domain.event.ExchangeRequestRejectedEvent;

public class ExchangeDomainServiceImpl implements ExchangeDomainService{
    @Override
    public ExchangeRequestCreatedEvent requestExchange(ExchangeRequest exchangeRequest) {
        //TODO : 교환 요청 알림
        return null;
    }

    @Override
    public ExchangeRequestAcceptedEvent acceptExchangeRequest(ExchangeRequest exchangeRequest) {
        // 수락 알림 발생
        return new ExchangeRequestAcceptedEvent(exchangeRequest.getRequestMemberId().getValue().toString());
    }

    @Override
    public ExchangeRequestRejectedEvent rejectExchangeRequest(ExchangeRequest exchangeRequest) {
        // 1. 요청한 사용자의 거절 카운트 +1, 거절 알림
        return new ExchangeRequestRejectedEvent(exchangeRequest.getRequestMemberId().getValue().toString());
    }
}
