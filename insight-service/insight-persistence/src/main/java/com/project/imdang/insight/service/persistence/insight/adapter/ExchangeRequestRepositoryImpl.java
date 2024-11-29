package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import com.project.imdang.insight.service.persistence.insight.entity.ExchangeRequestEntity;
import com.project.imdang.insight.service.persistence.insight.mapper.ExchangeRequestPersistenceMapper;
import com.project.imdang.insight.service.persistence.insight.repository.ExchangeRequestJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class ExchangeRequestRepositoryImpl implements ExchangeRequestRepository {

    private final ExchangeRequestJpaRepository exchangeRequestJpaRepository;
    private final ExchangeRequestPersistenceMapper exchangeRequestPersistenceMapper;

    @Transactional
    @Override
    public ExchangeRequest save(ExchangeRequest exchangeRequest) {
        ExchangeRequestEntity exchangeRequestEntity = exchangeRequestPersistenceMapper.exchangeToExchangeEntity(exchangeRequest);
        ExchangeRequestEntity saved = exchangeRequestJpaRepository.save(exchangeRequestEntity);
        return exchangeRequestPersistenceMapper.exchangeEntityToExchange(saved);
    }

    @Transactional
    @Override
    public void deleteById(ExchangeRequestId exchangeRequestId) {
        exchangeRequestJpaRepository.deleteById(exchangeRequestId.getValue());
    }
}
