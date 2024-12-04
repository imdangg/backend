package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.insight.service.domain.dto.exchange.list.ExchangesRequestedByOthersRequest;
import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestResponse;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExchangesRequestedByOthersHandler {

    private final ExchangeRequestRepository exchangeRequestRepository;
    public ListExchangeRequestResponse getExchanges(ExchangesRequestedByOthersRequest exchangesRequestedByOthersRequest) {
        // 1. 유저ID를 통해 ExchangeReqeust 목록 조회
        List<ExchangeRequest> exchangeRequestList = exchangeRequestRepository.findAllRequestedByOthers(exchangesRequestedByOthersRequest.getMemberId());

        // 2. 교환에 해당하는 인사이트 정보 추출
        // TODO : 연경님이 만드신 상세 활용 예정
        // List<Insight> insightList = ... //

        // 3. 반환
        // return new ListExchangeRequestResponse();
        return null;
    }
}
