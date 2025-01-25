package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.message.MemberCouponCancelledResponseMessage;
import com.project.imdang.insight.service.domain.ports.input.listener.MemberCouponCancelledResponseMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberCouponCancelledResponseMessageListenerImpl implements MemberCouponCancelledResponseMessageListener {

    private final RejectExchangeMemberCouponSaga rejectExchangeMemberCouponSaga;

    // TODO - vs TransactionalEventListener
    @EventListener
    @Override
    public void updated(MemberCouponCancelledResponseMessage memberCouponCancelledResponseMessage) {
        if (memberCouponCancelledResponseMessage.isCompleted()) {
            rejectExchangeMemberCouponSaga.process(memberCouponCancelledResponseMessage);
        } else {
            rejectExchangeMemberCouponSaga.rollback(memberCouponCancelledResponseMessage);
        }
    }
}
