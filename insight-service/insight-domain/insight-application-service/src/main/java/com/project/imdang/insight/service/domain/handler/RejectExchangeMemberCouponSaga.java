package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.message.MemberCouponCancelledResponseMessage;
import com.project.imdang.insight.service.domain.exception.InsightApplicationServiceException;
import com.project.imdang.saga.SagaStep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.project.imdang.domain.exception.ErrorCode.EXCHANGE_REQUEST_REJECT_FAILED;

@Slf4j
@RequiredArgsConstructor
@Component
public class RejectExchangeMemberCouponSaga implements SagaStep<MemberCouponCancelledResponseMessage> {

    @Override
    public void process(MemberCouponCancelledResponseMessage response) {
        log.info("MemberCoupon[id: {}] cancelled.", response.getMemberCouponId());
    }

    @Override
    public void rollback(MemberCouponCancelledResponseMessage response) {
        throw new InsightApplicationServiceException(EXCHANGE_REQUEST_REJECT_FAILED);
    }
}
