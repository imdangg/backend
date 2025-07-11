package com.project.imdang.member.messaging.listener;

import com.project.imdang.common.domain.event.DomainEventMessageListener;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.messaging.message.InsightAccusedEventMessage;
import com.project.imdang.member.domain.ports.input.service.MemberApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InsightAccusedEventMessageListener extends DomainEventMessageListener<InsightAccusedEventMessage> {

    private final MemberApplicationService memberApplicationService;

    public InsightAccusedEventMessageListener(MemberApplicationService memberApplicationService) {
        this.memberApplicationService = memberApplicationService;
    }

    @EventListener
    public void handle(InsightAccusedEventMessage event) {
        super.handle(event);
    }

    @Override
    public void process(InsightAccusedEventMessage domainEventMessage) {
        MemberId accusedMemberId = new MemberId(domainEventMessage.accusedMemberId());
        memberApplicationService.accuseMember(accusedMemberId);
    }
}
