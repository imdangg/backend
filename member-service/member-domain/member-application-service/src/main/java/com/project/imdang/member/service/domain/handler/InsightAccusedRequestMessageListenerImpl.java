package com.project.imdang.member.service.domain.handler;

import com.project.imdang.domain.message.InsightAccusedRequestMessage;
import com.project.imdang.domain.message.MemberAccusedResponseMessage;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.exception.MemberNotFoundException;
import com.project.imdang.member.service.domain.ports.input.listener.InsightAccusedRequestMessageListener;
import com.project.imdang.member.service.domain.ports.output.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InsightAccusedRequestMessageListenerImpl implements InsightAccusedRequestMessageListener {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final MemberRepository memberRepository;

    // TODO - vs TransactionalEventListener
    @EventListener
    public void handle(InsightAccusedRequestMessage insightAccusedRequestMessage) {

        MemberId accusedMemberId = new MemberId(insightAccusedRequestMessage.getAccusedMemberId());
        Member accusedMember = memberRepository.findById(accusedMemberId)
                .orElseThrow(() -> new MemberNotFoundException(accusedMemberId));
        accusedMember.increaseAccusedCount();
        memberRepository.save(accusedMember);

        MemberAccusedResponseMessage memberAccusedResponseMessage = new MemberAccusedResponseMessage(true, accusedMemberId.getValue());
        applicationEventPublisher.publishEvent(memberAccusedResponseMessage);
    }
}
