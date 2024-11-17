package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import com.project.imdang.insight.service.persistence.insight.entity.ExchangeRequestEntity;
import com.project.imdang.insight.service.persistence.insight.mapper.ExchangePersistenceMapper;
import com.project.imdang.insight.service.persistence.insight.repository.ExchangeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class ExchangeRequestRepositoryImpl implements ExchangeRequestRepository {

    private final ExchangeJpaRepository exchangeJpaRepository;
    private final ExchangePersistenceMapper exchangePersistenceMapper;

    @Transactional
    @Override
    public ExchangeRequest save(ExchangeRequest exchangeRequest) {
        ExchangeRequestEntity exchangeRequestEntity = exchangePersistenceMapper.exchangeToExchangeEntity(exchangeRequest);
        ExchangeRequestEntity saved = exchangeJpaRepository.save(exchangeRequestEntity);
        return exchangePersistenceMapper.exchangeEntityToExchange(saved);
    }

    @Transactional
    @Override
    public void deleteById(ExchangeRequestId exchangeRequestId) {
        exchangeJpaRepository.deleteById(exchangeRequestId.getValue());
    }
}
