package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import com.project.imdang.insight.service.persistence.insight.entity.ExchangeRequestEntity;
import com.project.imdang.insight.service.persistence.insight.mapper.ExchangeRequestPersistenceMapper;
import com.project.imdang.insight.service.persistence.insight.repository.ExchangeRequestJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
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

    @Transactional(readOnly = true)
    @Override
    public Optional<ExchangeRequest> findById(ExchangeRequestId exchangeRequestId) {
        Optional<ExchangeRequestEntity> findExchangeRequest = exchangeRequestJpaRepository.findById(exchangeRequestId.getValue());
        return findExchangeRequest.map(exchangeRequestPersistenceMapper::exchangeEntityToExchange);
    }

    @Override
    public Optional<ExchangeRequest> findByRequestMemberIdAndRequestedInsightId(MemberId requestMemberId, InsightId requestedInsightId) {
        return Optional.empty();
    }

    @Override
    public List<ExchangeRequest> findAllByRequestMemberId(MemberId requestMemberId) {
        return null;
    }

    @Override
    public List<ExchangeRequest> findAllByRequestedMemberId(MemberId requestedMemberId) {
        return null;
    }
}
