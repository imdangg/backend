package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.message.MemberCouponUsedResponseMessage;
import com.project.imdang.insight.service.domain.ports.input.listener.MemberCouponUsedResponseMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberCouponUsedResponseMessageListenerImpl implements MemberCouponUsedResponseMessageListener {

    private final RequestExchangeMemberCouponSaga requestExchangeMemberCouponSaga;

    // TODO - vs TransactionalEventListener
    @EventListener
    @Override
    public void updated(MemberCouponUsedResponseMessage memberCouponUsedResponseMessage) {
        if (memberCouponUsedResponseMessage.isCompleted()) {
            requestExchangeMemberCouponSaga.process(memberCouponUsedResponseMessage);
        } else {
            requestExchangeMemberCouponSaga.rollback(memberCouponUsedResponseMessage);
        }
    }
}
