package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.exchange.list.ExchangesRequestedByMeRequest;
import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestResponse;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExchangesRequestedByMeHandler {

    private final ExchangeRequestRepository exchangeRequestRepository;
    public ListExchangeRequestResponse getExchanges(ExchangesRequestedByMeRequest exchangesRequestedByMeRequest) {
        // 1. 유저ID를 통해 ExchangeReqeust 목록 조회
        List<ExchangeRequest> exchangeRequestList = exchangeRequestRepository.findAllRequestedByMe(exchangesRequestedByMeRequest.getMemberId());

        // 2. 교환에 해당하는 인사이트 정보 추출
        // TODO : 연경님이 만드신 상세 기능 활용 예정
        // List<Insight> insightList = ... //

        // 3. 반환
        // return new ListExchangeRequestResponse();
        return null;
    }

}
