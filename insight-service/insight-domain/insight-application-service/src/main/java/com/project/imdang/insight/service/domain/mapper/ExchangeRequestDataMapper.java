package com.project.imdang.insight.service.domain.mapper;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRequestDataMapper {

    public ExchangeRequest requestExchangeInsightCommandToExchangeRequest(RequestExchangeInsightCommand requestExchangeInsightCommand) {
       return ExchangeRequest.builder()
               .requestedInsightId(new InsightId(requestExchangeInsightCommand.getRequestedInsightId()))
               .requestMemberId(new MemberId(requestExchangeInsightCommand.getRequestMemberId()))
               .requestMemberInsightId(new InsightId(requestExchangeInsightCommand.getRequestMemberInsightId()))
               .build();
    }
}
