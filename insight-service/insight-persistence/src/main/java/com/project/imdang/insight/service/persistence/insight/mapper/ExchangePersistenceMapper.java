package com.project.imdang.insight.service.persistence.insight.mapper;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.Exchange;
import com.project.imdang.insight.service.persistence.insight.entity.ExchangeEntity;
import org.springframework.stereotype.Component;

@Component
public class ExchangePersistenceMapper {

    public ExchangeEntity exchangeToExchangeEntity(Exchange exchange) {
        return ExchangeEntity.builder()
                .id(exchange.getId().getValue())
                .requestMemberId(exchange.getRequestMemberId().getValue())
                .requestMemberInsightId(exchange.getRequestMemberInsightId().getValue())
                .requestedInsightId(exchange.getRequestedInsightId().getValue())
                .requestedAt(exchange.getRequestedAt())
                .respondedAt(exchange.getRespondedAt())
                .status(exchange.getStatus())
                .build();
    }

    public Exchange exchangeEntityToExchange(ExchangeEntity exchangeEntity) {
        return Exchange.builder()
                .requestMemberId(new MemberId(exchangeEntity.getRequestMemberId()))
                .requestMemberInsightId(new InsightId(exchangeEntity.getRequestMemberInsightId()))
                .requestedInsightId(new InsightId(exchangeEntity.getRequestedInsightId()))
                .requestedAt(exchangeEntity.getRequestedAt())
                .respondedAt(exchangeEntity.getRespondedAt())
                .status(exchangeEntity.getStatus())
                .build();
    }
}
