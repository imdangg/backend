package com.project.imdang.insight.service.domain.mapper;

import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ExchangeRequestDataMapper {

    public ExchangeRequest toExchangeRequest(RequestExchangeInsightCommand requestExchangeInsightCommand) {
       return new ExchangeRequest(requestExchangeInsightCommand.getRequestedInsightId(),
                requestExchangeInsightCommand.getRequestMemberInsightId(),
                requestExchangeInsightCommand.getRequestMemberInsightId());
    }
}
