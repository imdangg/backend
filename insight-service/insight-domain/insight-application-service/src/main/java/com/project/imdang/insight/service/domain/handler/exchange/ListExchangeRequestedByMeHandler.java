package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestedByMeQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ListExchangeRequestedByMeHandler {

    private final ExchangeRequestRepository exchangeRequestRepository;
    public List<InsightResponse> list(ListExchangeRequestedByMeQuery listExchangeRequestedByMeQuery) {
        // 1. 유저ID를 통해 ExchangeRequest 목록 조회
        // 2. 교환에 해당하는 인사이트 정보 추출
        // TODO : 연경님이 만드신 상세 기능 활용 예정
        // List<Insight> insightList = ... //

        // 3. 반환
        // return new ListExchangeRequestResponse();
        return null;
    }

}
