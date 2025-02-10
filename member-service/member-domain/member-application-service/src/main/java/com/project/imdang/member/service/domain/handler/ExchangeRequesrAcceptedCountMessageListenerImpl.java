package com.project.imdang.member.service.domain.handler;

import com.project.imdang.domain.message.ExchangeRequestAcceptedCountRequestMessage;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.MemberDomainService;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.exception.MemberNotFoundException;
import com.project.imdang.member.service.domain.ports.input.listener.ExchangeRequestAcceptedCountMessageListener;
import com.project.imdang.member.service.domain.ports.output.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExchangeRequesrAcceptedCountMessageListenerImpl implements ExchangeRequestAcceptedCountMessageListener {

    private final MemberRepository memberRepository;
    private final MemberDomainService memberDomainService;


    @Override
    public void handle(ExchangeRequestAcceptedCountRequestMessage exchangeRequestCountRequestMessage) {
        MemberId memberId = new MemberId(exchangeRequestCountRequestMessage.getMemberId());
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        member = memberDomainService.updateExchangeRequestAcceptedCount(member);
        memberRepository.save(member);
    }
}
