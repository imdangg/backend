package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.message.MemberAccusedResponseMessage;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.saga.SagaStep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class InsightAccuseMemberSaga implements SagaStep<MemberAccusedResponseMessage> {
    // insight accuse
    // insight accuse → increase accusedMember accusedCount (& update status)

    private final InsightDomainService insightDomainService;

    // accusedMember increase accusedCount 처리 완료
    @Override
    public void process(MemberAccusedResponseMessage response) {
        InsightId accusedInsightId = new InsightId(response.getAccusedInsightId());
    }

    // accusedMember increase accusedCount 처리 실패
    @Override
    public void rollback(MemberAccusedResponseMessage response) {
    }
}
