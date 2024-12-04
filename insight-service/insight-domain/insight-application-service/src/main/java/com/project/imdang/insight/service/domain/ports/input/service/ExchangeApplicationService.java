package com.project.imdang.insight.service.domain.ports.input.service;

import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.list.ExchangesRequestedByMeRequest;
import com.project.imdang.insight.service.domain.dto.exchange.list.ExchangesRequestedByOthersRequest;
import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightResponse;

import java.util.UUID;

public interface ExchangeApplicationService {
    RequestExchangeInsightResponse requestExchangeInsight(RequestExchangeInsightCommand requestExchangeInsightCommand);
    AcceptExchangeRequestResponse acceptExchangeRequest(AcceptExchangeRequestCommand acceptExchangeRequestCommand);
    RejectExchangeRequestResponse rejectExchangeRequest(RejectExchangeRequestCommand rejectExchangeRequestCommand);
    // 내가 요청한 내역
    ListExchangeRequestResponse exchangesRequestedByMe(ExchangesRequestedByMeRequest exchangesRequestedByMeRequest) ;
    // 다른 사람이 요청한 내역
    ListExchangeRequestResponse exchangesRequestedByOthers(ExchangesRequestedByOthersRequest exchangesRequestedByOthersRequest);
}
