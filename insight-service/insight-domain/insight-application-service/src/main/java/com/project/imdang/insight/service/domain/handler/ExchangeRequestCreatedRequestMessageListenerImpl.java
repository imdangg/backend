package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.message.ExchangeRequestCreatedRequestMessage;
import com.project.imdang.domain.message.MemberCouponPendingUpdatedResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExchangeRequestCreatedRequestMessageListenerImpl implements ExchangeRequestCreatedRequestMessageListener {

    private final ApplicationEventPublisher applicationEventPublisher;

    // TODO - vs TransactionalEventListener
    @EventListener
    public void handle(ExchangeRequestCreatedRequestMessage exchangeRequestCreatedRequestMessage) {

        // update
        MemberCouponPendingUpdatedResponseMessage memberCouponPendingUpdatedResponseMessage = new MemberCouponPendingUpdatedResponseMessage(
                true,
                exchangeRequestCreatedRequestMessage.getExchangeRequestId());
        applicationEventPublisher.publishEvent(memberCouponPendingUpdatedResponseMessage);
    }
}
