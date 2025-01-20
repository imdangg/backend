package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.message.MemberCouponPendingUpdatedResponseMessage;
import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.insight.service.domain.ExchangeDomainService;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.event.ExchangeRequestCreatedEvent;
import com.project.imdang.insight.service.domain.exception.InsightApplicationServiceException;
import com.project.imdang.saga.SagaStep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.project.imdang.domain.exception.ErrorCode.EXCHANGE_REQUEST_FAILED;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExchangeRequestMemberCouponSaga implements SagaStep<MemberCouponPendingUpdatedResponseMessage, ExchangeRequestCreatedEvent> {
    // request exchange using coupon
    // init exchange_request → memberCoupon "pending" → save exchange_request

    private final ExchangeDomainService exchangeDomainService;
    private final ExchangeRequestHelper exchangeRequestHelper;

    // memberCoupon "PENDING" 처리 완료
    @Override
    public ExchangeRequestCreatedEvent process(MemberCouponPendingUpdatedResponseMessage response) {

        ExchangeRequestId exchangeRequestId = new ExchangeRequestId(response.getExchangeRequestId());
        ExchangeRequest exchangeRequest = exchangeRequestHelper.get(exchangeRequestId);
        log.info("MemberCoupon[id: {}] status updated as PENDING.", exchangeRequest.getMemberCouponId());

        ExchangeRequestCreatedEvent exchangeRequestCreatedEvent = exchangeDomainService.completeCheckCoupon(exchangeRequest);
        exchangeRequestHelper.save(exchangeRequestCreatedEvent.getExchangeRequest());
//        eventPublisher.publish(exchangeRequestCreatedEvent);
        return exchangeRequestCreatedEvent;
    }

    // memberCoupon "PENDING" 처리 실패
    @Override
    public void rollback(MemberCouponPendingUpdatedResponseMessage response) {

        throw new InsightApplicationServiceException(EXCHANGE_REQUEST_FAILED);

        // 삭제 처리
//        ExchangeRequestId exchangeRequestId = new ExchangeRequestId(response.getExchangeRequestId());
//        exchangeRequestHelper.delete(exchangeRequestId);
//        return EmptyEvent.INSTANCE;
    }
}
