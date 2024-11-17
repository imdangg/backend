package com.project.imdang.insight.service.domain;

import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightResponse;
import com.project.imdang.insight.service.domain.handler.exchange.AcceptExchangeRequestCommandHandler;
import com.project.imdang.insight.service.domain.handler.exchange.RejectExchangeRequestCommandHandler;
import com.project.imdang.insight.service.domain.handler.exchange.RequestExchangeInsightCommandHandler;
import com.project.imdang.insight.service.domain.ports.input.service.ExchangeApplicationService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExchangeApplicationServiceImpl implements ExchangeApplicationService {

    private final RequestExchangeInsightCommandHandler requestExchangeInsightCommandHandler;
    private final AcceptExchangeRequestCommandHandler acceptExchangeRequestCommandHandler;
    private final RejectExchangeRequestCommandHandler rejectExchangeRequestCommandHandler;

    @Override
    public void listExchangeRequestCreatedByMe() {

    }

    @Override
    public void listExchangeRequestCreatedByOthers() {

    }

    @Override
    public RequestExchangeInsightResponse requestExchangeInsight(RequestExchangeInsightCommand requestExchangeInsightCommand) {
        return requestExchangeInsightCommandHandler.requestExchangeInsight(requestExchangeInsightCommand);
    }

    @Override
    public AcceptExchangeRequestResponse acceptExchangeRequest(AcceptExchangeRequestCommand acceptExchangeRequestCommand) {
        return acceptExchangeRequestCommandHandler.acceptExchangeRequest(acceptExchangeRequestCommand);
    }

    @Override
    public RejectExchangeRequestResponse rejectExchangeRequest(RejectExchangeRequestCommand rejectExchangeRequestCommand) {
        return rejectExchangeRequestCommandHandler.rejectExchangeRequest(rejectExchangeRequestCommand);
    }
}
