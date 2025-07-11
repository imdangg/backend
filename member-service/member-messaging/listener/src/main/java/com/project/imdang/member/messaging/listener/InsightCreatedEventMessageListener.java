package com.project.imdang.member.messaging.listener;

import com.project.imdang.common.domain.event.DomainEventMessageListener;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.messaging.message.InsightCreatedEventMessage;
import com.project.imdang.member.domain.ports.input.service.MemberApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InsightCreatedEventMessageListener extends DomainEventMessageListener<InsightCreatedEventMessage> {

    private final MemberApplicationService memberApplicationService;

    public InsightCreatedEventMessageListener(MemberApplicationService memberApplicationService) {
        this.memberApplicationService = memberApplicationService;
    }

    @EventListener
    public void handle(InsightCreatedEventMessage event) {
        super.handle(event);
    }

    @Override
    public void process(InsightCreatedEventMessage domainEventMessage) {
        //인사이트 생성 시 유저 정보 업데이트 (가장 최근 인사이트 작성 일시)
        MemberId createdMemberId = new MemberId(domainEventMessage.memberId());
        memberApplicationService.updateMember(createdMemberId);
    }
}
