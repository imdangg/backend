package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.domain.valueobject.ExchangeId;
import com.project.imdang.insight.service.domain.entity.Exchange;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRepository;
import com.project.imdang.insight.service.persistence.insight.entity.ExchangeEntity;
import com.project.imdang.insight.service.persistence.insight.mapper.ExchangePersistenceMapper;
import com.project.imdang.insight.service.persistence.insight.repository.ExchangeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class ExchangeRepositoryImpl implements ExchangeRepository {

    private final ExchangeJpaRepository exchangeJpaRepository;
    private final ExchangePersistenceMapper exchangePersistenceMapper;

    @Transactional
    @Override
    public Exchange save(Exchange exchange) {
        ExchangeEntity exchangeEntity = exchangePersistenceMapper.exchangeToExchangeEntity(exchange);
        ExchangeEntity saved = exchangeJpaRepository.save(exchangeEntity);
        return exchangePersistenceMapper.exchangeEntityToExchange(saved);
    }

    @Transactional
    @Override
    public void deleteById(ExchangeId exchangeId) {
        exchangeJpaRepository.deleteById(exchangeId.getValue());
    }
}
