package com.project.imdang.insight.service.persistence.insight.mapper;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.persistence.insight.entity.ExchangeRequestEntity;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRequestPersistenceMapper {

    public ExchangeRequestEntity exchangeToExchangeEntity(ExchangeRequest exchangeRequest) {
        return ExchangeRequestEntity.builder()
                .id(exchangeRequest.getId().getValue())
                .requestMemberId(exchangeRequest.getRequestMemberId().getValue())
                .requestMemberInsightId(exchangeRequest.getRequestMemberInsightId().getValue())
                .requestedInsightId(exchangeRequest.getRequestedInsightId().getValue())
                .requestedAt(exchangeRequest.getRequestedAt())
                .respondedAt(exchangeRequest.getRespondedAt())
                .status(exchangeRequest.getStatus())
                .build();
    }

    public ExchangeRequest exchangeEntityToExchange(ExchangeRequestEntity exchangeRequestEntity) {
        return ExchangeRequest.builder()
                .requestMemberId(new MemberId(exchangeRequestEntity.getRequestMemberId()))
                .requestMemberInsightId(new InsightId(exchangeRequestEntity.getRequestMemberInsightId()))
                .requestedInsightId(new InsightId(exchangeRequestEntity.getRequestedInsightId()))
                .requestedAt(exchangeRequestEntity.getRequestedAt())
                .respondedAt(exchangeRequestEntity.getRespondedAt())
                .status(exchangeRequestEntity.getStatus())
                .build();
    }
}
