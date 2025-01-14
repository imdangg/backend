package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.message.MemberCouponPendingUpdatedResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberCouponPendingUpdatedResponseMessageListenerImpl implements MemberCouponPendingUpdatedResponseMessageListener {

    private final ExchangeRequestMemberCouponSaga exchangeRequestMemberCouponSaga;

    // TODO - vs TransactionalEventListener
    @EventListener
    @Override
    public void updated(MemberCouponPendingUpdatedResponseMessage memberCouponPendingUpdatedResponseMessage) {
        if (memberCouponPendingUpdatedResponseMessage.isCompleted()) {
            exchangeRequestMemberCouponSaga.process(memberCouponPendingUpdatedResponseMessage);
        } else {
            exchangeRequestMemberCouponSaga.rollback(memberCouponPendingUpdatedResponseMessage);
        }
    }
}
