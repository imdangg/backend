package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.event.EmptyEvent;
import com.project.imdang.domain.message.MemberCouponPendingUpdatedResponseMessage;
import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.event.EventPublisher;
import com.project.imdang.insight.service.domain.ExchangeDomainService;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.event.ExchangeRequestCreatedEvent;
import com.project.imdang.insight.service.domain.exception.ExchangeDomainException;
import com.project.imdang.insight.service.domain.exception.ExchangeRequestNotFoundException;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import com.project.imdang.saga.SagaStep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExchangeRequestMemberCouponSaga implements SagaStep<MemberCouponPendingUpdatedResponseMessage, ExchangeRequestCreatedEvent, EmptyEvent> {
    // request exchange using coupon
    // init exchange_request → memberCoupon "pending" → save exchange_request

    private final ExchangeDomainService exchangeDomainService;
    private final ExchangeRequestRepository exchangeRequestRepository;

    private final EventPublisher eventPublisher;

    // memberCoupon "PENDING" 처리 완료
    @Override
    public ExchangeRequestCreatedEvent process(MemberCouponPendingUpdatedResponseMessage response) {

        ExchangeRequestId exchangeRequestId = new ExchangeRequestId(response.getExchangeRequestId());
        ExchangeRequest exchangeRequest = exchangeRequestRepository.findById(exchangeRequestId)
                .orElseThrow(() -> new ExchangeRequestNotFoundException(exchangeRequestId));
        log.info("MemberCoupon[id: {}] updated as PENDING.", exchangeRequest.getMemberCouponId());

        ExchangeRequestCreatedEvent exchangeRequestCreatedEvent = exchangeDomainService.completeCheckCoupon(exchangeRequest);
        save(exchangeRequestCreatedEvent.getExchangeRequest());
        eventPublisher.publish(exchangeRequestCreatedEvent);
        return exchangeRequestCreatedEvent;
    }

    // memberCoupon "PENDING" 처리 실패
    @Override
    public EmptyEvent rollback(MemberCouponPendingUpdatedResponseMessage response) {
        ExchangeRequestId exchangeRequestId = new ExchangeRequestId(response.getExchangeRequestId());
        // TODO - 로그
        log.info("Failed!");
        // TODO - CHECK : 삭제 처리
        exchangeRequestRepository.deleteById(exchangeRequestId);
        return EmptyEvent.INSTANCE;
    }

    private ExchangeRequest save(ExchangeRequest exchangeRequest) {
        ExchangeRequest savedExchangeRequest = exchangeRequestRepository.save(exchangeRequest);
        if(savedExchangeRequest == null) {
            String errorMessage = "Could not save ExchangeRequest!";
            log.error(errorMessage);
            throw new ExchangeDomainException(errorMessage);
        }
        log.info("ExchangeRequest[id: {}] is saved.", savedExchangeRequest.getId().getValue());
        return savedExchangeRequest;
    }
}
