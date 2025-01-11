package com.project.imdang.insight.service.domain;

import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.event.ExchangeRequestAcceptedEvent;
import com.project.imdang.insight.service.domain.event.ExchangeRequestCreatedEvent;
import com.project.imdang.insight.service.domain.event.ExchangeRequestRejectedEvent;

public interface ExchangeDomainService {
    ExchangeRequestCreatedEvent requestExchange(ExchangeRequest exchangeRequest, Snapshot requestedSnapshot, Snapshot requestMemberSnapshot);
    ExchangeRequest requestExchangeWithCoupon(ExchangeRequest exchangeRequest, Snapshot requestedSnapshot, MemberCouponId memberCouponId);
    ExchangeRequestCreatedEvent completeCheckCoupon(ExchangeRequest exchangeRequest);
    ExchangeRequestAcceptedEvent acceptExchangeRequest(ExchangeRequest exchangeRequest);
    ExchangeRequestRejectedEvent rejectExchangeRequest(ExchangeRequest exchangeRequest);
}
