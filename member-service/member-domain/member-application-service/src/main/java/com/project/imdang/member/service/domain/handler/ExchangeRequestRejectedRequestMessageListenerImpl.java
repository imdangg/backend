package com.project.imdang.member.service.domain.handler;

import com.project.imdang.domain.message.ExchangeRequestRejectedRequestMessage;
import com.project.imdang.domain.message.MemberCouponCancelledResponseMessage;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.member.service.domain.MemberCouponDomainService;
import com.project.imdang.member.service.domain.entity.MemberCoupon;
import com.project.imdang.member.service.domain.exception.MemberCouponNotFoundException;
import com.project.imdang.member.service.domain.ports.input.listener.ExchangeRequestRejectedRequestMessageListener;
import com.project.imdang.member.service.domain.ports.output.MemberCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExchangeRequestRejectedRequestMessageListenerImpl implements ExchangeRequestRejectedRequestMessageListener {

    private final ApplicationEventPublisher applicationEventPublisher;

    private final MemberCouponRepository memberCouponRepository;
    private final MemberCouponDomainService memberCouponDomainService;

    // TODO - vs TransactionalEventListener
    @EventListener
    public void handle(ExchangeRequestRejectedRequestMessage exchangeRequestRejectedRequestMessage) {

        MemberCouponId memberCouponId = new MemberCouponId(exchangeRequestRejectedRequestMessage.getMemberCouponId());
        MemberCoupon memberCoupon = memberCouponRepository.findById(memberCouponId)
                .orElseThrow(() -> new MemberCouponNotFoundException(memberCouponId));
        memberCouponDomainService.cancel(memberCoupon);
        memberCouponRepository.save(memberCoupon);

        // update
        MemberCouponCancelledResponseMessage memberCouponCancelledResponseMessage = new MemberCouponCancelledResponseMessage(
                true,
                memberCoupon.getId().getValue());
        applicationEventPublisher.publishEvent(memberCouponCancelledResponseMessage);
    }
}
