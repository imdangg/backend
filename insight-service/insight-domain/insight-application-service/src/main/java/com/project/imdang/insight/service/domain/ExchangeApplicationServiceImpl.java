package com.project.imdang.insight.service.domain;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightResponse;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.handler.exchange.AcceptExchangeCommandHandler;
import com.project.imdang.insight.service.domain.handler.exchange.RejectExchangeCommandHandler;
import com.project.imdang.insight.service.domain.handler.exchange.RequestExchangeCommandHandler;
import com.project.imdang.insight.service.domain.ports.input.service.ExchangeApplicationService;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ExchangeApplicationServiceImpl implements ExchangeApplicationService {

    private final RequestExchangeCommandHandler requestExchangeCommandHandler;
    private final AcceptExchangeCommandHandler acceptExchangeCommandHandler;
    private final RejectExchangeCommandHandler rejectExchangeCommandHandler;
    private final ExchangeRequestRepository exchangeRequestRepository;

    @Override
    public RequestExchangeInsightResponse requestExchangeInsight(RequestExchangeInsightCommand requestExchangeInsightCommand) {
       return requestExchangeCommandHandler.request(requestExchangeInsightCommand);
    }

    @Override
    public AcceptExchangeRequestResponse acceptExchangeRequest(AcceptExchangeRequestCommand acceptExchangeRequestCommand) {
        return acceptExchangeCommandHandler.acceptExchangeRequest(acceptExchangeRequestCommand);
    }

    @Override
    public RejectExchangeRequestResponse rejectExchangeRequest(RejectExchangeRequestCommand rejectExchangeRequestCommand) {
       return rejectExchangeCommandHandler.rejectExchangeRequest(rejectExchangeRequestCommand);
    }

    @Override
    public ListExchangeRequestResponse listExchangeRequestCreatedByMe(String memberId) {
        // 1. 유저ID를 통해 ExchangeReqeust 목록 조회
        List<ExchangeRequest> exchangeRequestList = exchangeRequestRepository.findAllByMe(new MemberId(UUID.fromString(memberId)));

        // 2. 교환에 해당하는 인사이트 정보 추출
        // TODO : 연경님이 만드신 상세 기능 활용 예정
        // List<Insight> insightList = ... //

        // 3. 반환
        // return new ListExchangeRequestResponse();
        return null;
    }

    @Override
    public ListExchangeRequestResponse listExchangeRequestCreatedByOthers(String memberId) {

        // 1. 유저ID를 통해 ExchangeReqeust 목록 조회
        List<ExchangeRequest> exchangeRequestList = exchangeRequestRepository.findAllByOther(new MemberId(UUID.fromString(memberId)));

        // 2. 교환에 해당하는 인사이트 정보 추출
        // TODO : 연경님이 만드신 상세 활용 예정
        // List<Insight> insightList = ... //

        // 3. 반환
        // return new ListExchangeRequestResponse();
        return null;
    }
}
