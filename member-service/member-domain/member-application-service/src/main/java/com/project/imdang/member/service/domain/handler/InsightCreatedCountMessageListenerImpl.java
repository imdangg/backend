package com.project.imdang.member.service.domain.handler;

import com.project.imdang.domain.message.InsightCreatedCountRequestMessage;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.MemberDomainService;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.exception.MemberNotFoundException;
import com.project.imdang.member.service.domain.ports.input.listener.InsightCreatedCountMessageListener;
import com.project.imdang.member.service.domain.ports.output.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InsightCreatedCountMessageListenerImpl implements InsightCreatedCountMessageListener {

    private final MemberRepository memberRepository;
    private final MemberDomainService memberDomainService;

    @Override
    @Async
    @EventListener
    public void handle(InsightCreatedCountRequestMessage insightCreatedCountRequestMessage) {
        MemberId memberId = new MemberId(insightCreatedCountRequestMessage.getMemberId());
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        member = memberDomainService.updateInsightCreatedCount(member);
        memberRepository.save(member);
    }
}
