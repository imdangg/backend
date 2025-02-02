package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.message.MemberAccusedResponseMessage;
import com.project.imdang.insight.service.domain.ports.input.listener.MemberAccusedResponseMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberAccusedResponseMessageListenerImpl implements MemberAccusedResponseMessageListener {

    private final AccuseInsightMemberSaga accuseInsightMemberSaga;

    // TODO - vs TransactionalEventListener
    @EventListener
    @Override
    public void updated(MemberAccusedResponseMessage memberAccusedResponseMessage) {
        if (memberAccusedResponseMessage.isCompleted()) {
            accuseInsightMemberSaga.process(memberAccusedResponseMessage);
        } else {
            accuseInsightMemberSaga.rollback(memberAccusedResponseMessage);
        }
    }
}
