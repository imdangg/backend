package com.project.imdang.ports.input.service;

import com.project.imdang.dto.AcceptExchangeRequestCommand;
import com.project.imdang.dto.RejectExchangeRequestCommand;
import com.project.imdang.dto.RequestExchangeInsightCommand;

public interface ExchangeApplicationService {
    void requestExchangeInsight(RequestExchangeInsightCommand requestExchangeInsightCommand);
    // TODO - CHECK : vs responseExchangeRequest
    void acceptExchangeRequest(AcceptExchangeRequestCommand acceptExchangeRequestCommand);
    void rejectExchangeRequest(RejectExchangeRequestCommand rejectExchangeRequestCommand);
}
