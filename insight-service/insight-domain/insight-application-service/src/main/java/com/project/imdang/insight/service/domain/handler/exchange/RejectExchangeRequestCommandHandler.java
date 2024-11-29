package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestResponse;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RejectExchangeRequestCommandHandler {
    // TODO - CHECK : 교환 요청/거절/승인 내역을 따로 저장해서 GROUP BY로 COUNT하는 방법
    // TODO - CHECK : 거절 횟수로 쿠폰 발급하는 것은 배치로? 비동기(kafka)? 로직에서 바로 처리
// 교환 요청한 상대방의 rejectedCount + 1 → 횟수 비교해서 쿠폰 발급
    @Transactional
    public RejectExchangeRequestResponse rejectExchangeRequest(RejectExchangeRequestCommand rejectExchangeRequestCommand) {
        return null;
    }
}
