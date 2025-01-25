package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.message.MemberCouponUsedResponseMessage;
import com.project.imdang.insight.service.domain.exception.InsightApplicationServiceException;
import com.project.imdang.saga.SagaStep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.project.imdang.domain.exception.ErrorCode.EXCHANGE_REQUEST_FAILED;

@Slf4j
@RequiredArgsConstructor
@Component
public class RequestExchangeMemberCouponSaga implements SagaStep<MemberCouponUsedResponseMessage> {

    // memberCoupon "USED" 처리 완료
    @Override
    public void process(MemberCouponUsedResponseMessage response) {
        log.info("MemberCoupon[id: {}] used.", response.getMemberCouponId());
    }

    // memberCoupon "USED" 처리 실패
    @Override
    public void rollback(MemberCouponUsedResponseMessage response) {
        throw new InsightApplicationServiceException(EXCHANGE_REQUEST_FAILED);
    }
}
