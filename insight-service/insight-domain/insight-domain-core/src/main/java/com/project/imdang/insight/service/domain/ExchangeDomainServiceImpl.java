package com.project.imdang.insight.service.domain;

import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.event.ExchangeRequestAcceptedEvent;
import com.project.imdang.insight.service.domain.event.ExchangeRequestCreatedEvent;
import com.project.imdang.insight.service.domain.event.ExchangeRequestRejectedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;

@Slf4j
public class ExchangeDomainServiceImpl implements ExchangeDomainService {

    @Override
    public ExchangeRequestCreatedEvent requestExchange(ExchangeRequest exchangeRequest, Snapshot requestedSnapshot, Snapshot requestMemberSnapshot) {
        exchangeRequest.initialize(requestedSnapshot, requestMemberSnapshot, null);
        log.info("ExchangeRequest[id: {}] is initialized.", exchangeRequest.getId().getValue());
        return new ExchangeRequestCreatedEvent(exchangeRequest, ZonedDateTime.now());
    }

    @Override
    public ExchangeRequest requestExchangeWithCoupon(ExchangeRequest exchangeRequest, Snapshot requestedSnapshot, MemberCouponId memberCouponId) {
        exchangeRequest.initialize(requestedSnapshot, null, memberCouponId);
        log.info("ExchangeRequest[id: {}] is initialized.", exchangeRequest.getId().getValue());
        return exchangeRequest;
    }

    @Override
    public ExchangeRequestCreatedEvent completeCheckCoupon(ExchangeRequest exchangeRequest) {
        exchangeRequest.completeCheckCoupon();
        log.info("Coupon of ExchangeRequest[id: {}] is checked.", exchangeRequest.getId().getValue());
        return new ExchangeRequestCreatedEvent(exchangeRequest, ZonedDateTime.now());
    }

    @Override
    public ExchangeRequestAcceptedEvent acceptExchangeRequest(ExchangeRequest exchangeRequest) {
        exchangeRequest.accept();
        log.info("ExchangeRequest[id: {}] is accepted.", exchangeRequest.getId().getValue());
        return new ExchangeRequestAcceptedEvent(exchangeRequest, ZonedDateTime.now());
    }

    @Override
    public ExchangeRequestRejectedEvent rejectExchangeRequest(ExchangeRequest exchangeRequest) {
        exchangeRequest.reject();
        log.info("ExchangeRequest[id: {}] is rejected.", exchangeRequest.getId().getValue());
        // TODO : 요청한 사용자의 거절 카운트 + 1, 거절 알림
        return new ExchangeRequestRejectedEvent(exchangeRequest, ZonedDateTime.now());
    }
}
