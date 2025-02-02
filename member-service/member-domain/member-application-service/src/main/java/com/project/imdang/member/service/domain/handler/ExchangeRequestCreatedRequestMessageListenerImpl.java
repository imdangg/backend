package com.project.imdang.member.service.domain.handler;

import com.project.imdang.domain.message.ExchangeRequestCreatedRequestMessage;
import com.project.imdang.domain.message.MemberCouponUsedResponseMessage;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.member.service.domain.MemberCouponDomainService;
import com.project.imdang.member.service.domain.entity.MemberCoupon;
import com.project.imdang.member.service.domain.exception.MemberCouponNotFoundException;
import com.project.imdang.member.service.domain.ports.input.listener.ExchangeRequestCreatedRequestMessageListener;
import com.project.imdang.member.service.domain.ports.output.MemberCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExchangeRequestCreatedRequestMessageListenerImpl implements ExchangeRequestCreatedRequestMessageListener {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final MemberCouponRepository memberCouponRepository;

    private final MemberCouponDomainService memberCouponDomainService;

    // TODO - vs TransactionalEventListener
    @EventListener
    public void handle(ExchangeRequestCreatedRequestMessage exchangeRequestCreatedRequestMessage) {

        MemberCouponId memberCouponId = new MemberCouponId(exchangeRequestCreatedRequestMessage.getMemberCouponId());
        MemberCoupon memberCoupon = memberCouponRepository.findById(memberCouponId)
                .orElseThrow(() -> new MemberCouponNotFoundException(memberCouponId));
        memberCouponDomainService.use(memberCoupon);
        memberCouponRepository.save(memberCoupon);

        // update
        MemberCouponUsedResponseMessage memberCouponUsedResponseMessage
                = new MemberCouponUsedResponseMessage(true, memberCoupon.getId().getValue());
        applicationEventPublisher.publishEvent(memberCouponUsedResponseMessage);
    }
}
