package com.project.imdang.member.messaging.listener;

import com.project.imdang.common.domain.event.DomainEventMessageListener;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.messaging.message.InsightDeletedEventMessage;
import com.project.imdang.member.domain.ports.input.service.MemberApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InsightDeletedEventMessageListener extends DomainEventMessageListener<InsightDeletedEventMessage> {

    private final MemberApplicationService memberApplicationService;

    public InsightDeletedEventMessageListener(MemberApplicationService memberApplicationService) {
        this.memberApplicationService = memberApplicationService;
    }

    @Override
    public void process(InsightDeletedEventMessage domainEventMessage) {
        MemberId memberId = new MemberId(domainEventMessage.memberId());
        memberApplicationService.decreaseInsightCount(memberId);
    }
}
