package com.project.imdang.insight.service.domain.ports.input.service;

import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestedByMeQuery;
import com.project.imdang.insight.service.domain.dto.exchange.list.ListExchangeRequestedByOthersQuery;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestResponse;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import org.springframework.data.domain.Page;

public interface ExchangeApplicationService {
    RequestExchangeInsightResponse requestExchangeInsight(RequestExchangeInsightCommand requestExchangeInsightCommand);
    AcceptExchangeRequestResponse acceptExchangeRequest(AcceptExchangeRequestCommand acceptExchangeRequestCommand);
    RejectExchangeRequestResponse rejectExchangeRequest(RejectExchangeRequestCommand rejectExchangeRequestCommand);
    // 내가 요청한 내역
    Page<InsightResponse> listExchangeRequestedByMe(ListExchangeRequestedByMeQuery listExchangeRequestedByMeQuery) ;
    // 다른 사람이 요청한 내역
    Page<InsightResponse> listExchangeRequestedByOthers(ListExchangeRequestedByOthersQuery listExchangeRequestedByOthersQuery);
}
