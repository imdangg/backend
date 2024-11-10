package com.project.imdang.insight.service.domain;

import com.project.imdang.insight.service.domain.dto.AcceptExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.AcceptExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.RejectExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.RejectExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.dto.RequestExchangeInsightResponse;
import com.project.imdang.insight.service.domain.handler.AcceptExchangeRequestCommandHandler;
import com.project.imdang.insight.service.domain.handler.RejectExchangeRequestCommandHandler;
import com.project.imdang.insight.service.domain.handler.RequestExchangeInsightCommandHandler;
import com.project.imdang.insight.service.domain.ports.input.service.ExchangeApplicationService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExchangeApplicationServiceImpl implements ExchangeApplicationService {

    private final RequestExchangeInsightCommandHandler requestExchangeInsightCommandHandler;
    private final AcceptExchangeRequestCommandHandler acceptExchangeRequestCommandHandler;
    private final RejectExchangeRequestCommandHandler rejectExchangeRequestCommandHandler;

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
