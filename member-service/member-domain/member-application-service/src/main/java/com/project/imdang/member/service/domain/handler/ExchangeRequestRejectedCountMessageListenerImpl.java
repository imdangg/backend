package com.project.imdang.member.service.domain.handler;

import com.project.imdang.domain.message.ExchangeRequestRejectedCountRequestMessage;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.MemberDomainService;
import com.project.imdang.member.service.domain.dto.coupon.IssueMemberCouponCommand;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.exception.MemberNotFoundException;
import com.project.imdang.member.service.domain.handler.coupon.IssueMemberCouponCommandHandler;
import com.project.imdang.member.service.domain.ports.input.listener.ExchangeRequestRejectedCountMessageListener;
import com.project.imdang.member.service.domain.ports.output.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExchangeRequestRejectedCountMessageListenerImpl implements ExchangeRequestRejectedCountMessageListener {

    private final static String FRIST_CHEERUP = "FirstCheerup";
    private final static String SECOND_CHEERUP = "SecondCheerup";
    private final static String THIRD_CHEERUP = "ThirdCheerup";

    private final MemberRepository memberRepository;
    private final MemberDomainService memberDomainService;
    private final IssueMemberCouponCommandHandler issueMemberCouponCommandHandler;

    // TODO - vs TransactionalEventListener
    @Override
    @Async
    @EventListener
    public void handle(ExchangeRequestRejectedCountRequestMessage exchangeRequestRejectedCountRequestMessage) {
        // 1. 받은 사용자 아이디로 유저 찾기
        MemberId memberId = new MemberId(exchangeRequestRejectedCountRequestMessage.getMemberId());
        Member member = memberRepository.findById(memberId)
               .orElseThrow(() -> new MemberNotFoundException(memberId));
        // 2. 유저의 거절 횟수++
        member = memberDomainService.updateRejectedCount(member);
        // 3. 만약 카운트가 된다면? -> 쿠폰 발급!
        checkRejectedCountAndIssueCoupon(member);
    }

    private void checkRejectedCountAndIssueCoupon(Member member) {
        switch (member.getRejectedCount()) {
            case 5:
                issueMemberCouponCommandHandler.issue(new IssueMemberCouponCommand(member.getId().getValue(), FRIST_CHEERUP));
                break;
            case 10:
                issueMemberCouponCommandHandler.issue(new IssueMemberCouponCommand(member.getId().getValue(), SECOND_CHEERUP));
                break;
            case 20:
                issueMemberCouponCommandHandler.issue(new IssueMemberCouponCommand(member.getId().getValue(), THIRD_CHEERUP));
                break;
        }
    }
}
