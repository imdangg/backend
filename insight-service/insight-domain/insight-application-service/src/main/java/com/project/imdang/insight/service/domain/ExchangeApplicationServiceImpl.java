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
        // 1. 교환 도메인 생성
        ExchangeRequest exchangeRequest = requestExchangeCommandHandler.request(requestExchangeInsightCommand);

        // 2. 저장
        ExchangeRequest savedRequest = exchangeRequestRepository.save(exchangeRequest);
        return new RequestExchangeInsightResponse(savedRequest.getId().getValue().toString());
    }

    @Override
    public AcceptExchangeRequestResponse acceptExchangeRequest(AcceptExchangeRequestCommand acceptExchangeRequestCommand) {
        // 1. 받아온 교환요청ID로 도메인 찾기
        ExchangeRequestId requestId = acceptExchangeCommandHandler.getExchangeRequestId(acceptExchangeRequestCommand);
        ExchangeRequest exchangeRequest = exchangeRequestRepository.findExchangeRequest(requestId);
        // 2. 수락 상태로 정보 업데이트
        ExchangeRequest acceptedExchangeRequest = acceptExchangeCommandHandler.acceptExchangeRequest(exchangeRequest);
        // 3. 반환
        exchangeRequestRepository.save(acceptedExchangeRequest);
        return new AcceptExchangeRequestResponse(acceptedExchangeRequest.getStatus().name());
    }

    @Override
    public RejectExchangeRequestResponse rejectExchangeRequest(RejectExchangeRequestCommand rejectExchangeRequestCommand) {
        // 1. 받아온 교환요청ID로 도메인 찾기
        ExchangeRequestId requestId = rejectExchangeCommandHandler.getExchangeRequestId(rejectExchangeRequestCommand);
        ExchangeRequest exchangeRequest = exchangeRequestRepository.findExchangeRequest(requestId);
        // 2. 거절 상태로 정보 업데이트
        ExchangeRequest acceptedExchangeRequest = rejectExchangeCommandHandler.rejectExchangeRequest(exchangeRequest);
        // 3. 반환
        exchangeRequestRepository.save(acceptedExchangeRequest);
        return new RejectExchangeRequestResponse(acceptedExchangeRequest.getStatus().name());
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
