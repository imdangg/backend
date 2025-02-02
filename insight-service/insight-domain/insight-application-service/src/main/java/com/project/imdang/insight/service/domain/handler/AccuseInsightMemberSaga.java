package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.message.MemberAccusedResponseMessage;
import com.project.imdang.insight.service.domain.exception.InsightApplicationServiceException;
import com.project.imdang.saga.SagaStep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.project.imdang.domain.exception.ErrorCode.MEMBER_ACCUSE_FAILED;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccuseInsightMemberSaga implements SagaStep<MemberAccusedResponseMessage> {

    @Override
    public void process(MemberAccusedResponseMessage response) {
        log.info("Member[id: {}] accused.", response.getAccusedMemberId());
    }

    @Override
    public void rollback(MemberAccusedResponseMessage response) {
        throw new InsightApplicationServiceException(MEMBER_ACCUSE_FAILED);
    }
}
