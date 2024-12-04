package com.project.imdang.insight.service.domain;

import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.list.ExchangesRequestedByMeRequest;
import com.project.imdang.insight.service.domain.dto.exchange.list.ExchangesRequestedByOthersRequest;
import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightResponse;
import com.project.imdang.insight.service.domain.handler.exchange.*;
import com.project.imdang.insight.service.domain.ports.input.service.ExchangeApplicationService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ExchangeApplicationServiceImpl implements ExchangeApplicationService {

    private final RequestExchangeCommandHandler requestExchangeCommandHandler;
    private final AcceptExchangeCommandHandler acceptExchangeCommandHandler;
    private final RejectExchangeCommandHandler rejectExchangeCommandHandler;
    private final ExchangesRequestedByMeHandler exchangesRequestedByMeHandler;
    private final ExchangesRequestedByOthersHandler exchangesRequestedByOthersHandler;

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
    public ListExchangeRequestResponse exchangesRequestedByMe(ExchangesRequestedByMeRequest exchangesRequestedByMeRequest) {
        return exchangesRequestedByMeHandler.getExchanges(exchangesRequestedByMeRequest);
    }

    @Override
    public ListExchangeRequestResponse exchangesRequestedByOthers(ExchangesRequestedByOthersRequest exchangesRequestedByOthersRequest) {
        return exchangesRequestedByOthersHandler.getExchanges(exchangesRequestedByOthersRequest);
    }
}
