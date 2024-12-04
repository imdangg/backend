package com.project.imdang.insight.service.domain;

import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestedByMeQuery;
import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestedByOthersQuery;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.handler.exchange.*;
import com.project.imdang.insight.service.domain.ports.input.service.ExchangeApplicationService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ExchangeApplicationServiceImpl implements ExchangeApplicationService {

    private final RequestExchangeCommandHandler requestExchangeCommandHandler;
    private final AcceptExchangeCommandHandler acceptExchangeCommandHandler;
    private final RejectExchangeCommandHandler rejectExchangeCommandHandler;
    private final ListExchangeRequestedByMeHandler listExchangeRequestedByMeHandler;
    private final ListExchangeRequestedByOthersHandler listExchangeRequestedByOthersHandler;

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
    public List<InsightResponse> listExchangeRequestedByMe(ListExchangeRequestedByMeQuery listExchangeRequestedByMeQuery) {
        return listExchangeRequestedByMeHandler.list(listExchangeRequestedByMeQuery);
    }

    @Override
    public List<InsightResponse> listExchangeRequestedByOthers(ListExchangeRequestedByOthersQuery listExchangeRequestedByOthersQuery) {
        return listExchangeRequestedByOthersHandler.list(listExchangeRequestedByOthersQuery);
    }
}
