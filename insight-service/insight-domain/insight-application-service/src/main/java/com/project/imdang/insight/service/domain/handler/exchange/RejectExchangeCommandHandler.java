package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.insight.service.domain.ExchangeDomainService;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestCommand;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.event.ExchangeRequestRejectedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class RejectExchangeCommandHandler {
    // TODO - CHECK : 교환 요청/거절/승인 내역을 따로 저장해서 GROUP BY로 COUNT하는 방법
    // TODO - CHECK : 거절 횟수로 쿠폰 발급하는 것은 배치로? 비동기(kafka)? 로직에서 바로 처리
// 교환 요청한 상대방의 rejectedCount + 1 → 횟수 비교해서 쿠폰 발급
    private final ExchangeDomainService exchangeDomainService;

    public ExchangeRequestId getExchangeRequestId(RejectExchangeRequestCommand rejectExchangeRequestCommand) {
        return new ExchangeRequestId(UUID.fromString(rejectExchangeRequestCommand.getExchangeId()));
    }

    public ExchangeRequest rejectExchangeRequest(ExchangeRequest exchangeRequest) {
        //TODO - CHECK : exchangeDomainService를 타고 가는게 맞을지?
        exchangeRequest.reject();
        log.info("교환요청 거절 완료 {}", exchangeRequest.getStatus());

        log.info("거절 횟수 +1 이벤트 및 알림 발생");
        ExchangeRequestRejectedEvent rejectedEvent = exchangeDomainService.rejectExchangeRequest(exchangeRequest);

        //TODO : publish
        return exchangeRequest;
    }
}
