package com.project.imdang.member.messaging.listener;

import com.project.imdang.common.domain.event.DomainEventMessageListener;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.messaging.message.InsightCreatedEventMessage;
import com.project.imdang.member.domain.ports.input.service.MemberApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InsightCreatedEventMessageListener extends DomainEventMessageListener<InsightCreatedEventMessage> {

    private final MemberApplicationService memberApplicationService;

    public InsightCreatedEventMessageListener(MemberApplicationService memberApplicationService) {
        this.memberApplicationService = memberApplicationService;
    }

    @Override
    public void process(InsightCreatedEventMessage domainEventMessage) {
        MemberId memberId = new MemberId(domainEventMessage.memberId());
        memberApplicationService.increaseInsightCount(memberId);
    }
}
