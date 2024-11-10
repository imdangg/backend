package com.project.imdang.insight.service.domain.ports.input.service;

import com.project.imdang.insight.service.domain.dto.AcceptExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.AcceptExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.RejectExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.RejectExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.dto.RequestExchangeInsightResponse;

public interface ExchangeApplicationService {
    RequestExchangeInsightResponse requestExchangeInsight(RequestExchangeInsightCommand requestExchangeInsightCommand);
    // TODO - CHECK : vs responseExchangeRequest
    AcceptExchangeRequestResponse acceptExchangeRequest(AcceptExchangeRequestCommand acceptExchangeRequestCommand);
    RejectExchangeRequestResponse rejectExchangeRequest(RejectExchangeRequestCommand rejectExchangeRequestCommand);
}
