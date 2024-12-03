package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
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

import java.util.*;
import java.util.stream.Collectors;

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
    public Optional<ExchangeRequest> find(UUID exchangeRequestId) {
        Optional<ExchangeRequestEntity> findExchangeRequest = exchangeRequestJpaRepository.findById(exchangeRequestId);
        return findExchangeRequest.map(exchangeRequestPersistenceMapper::exchangeEntityToExchange);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ExchangeRequest> findAllByMe(MemberId memberId) {
        List<ExchangeRequestEntity> findExchangeRequestEntity = exchangeRequestJpaRepository.findAllByRequestMemberId(memberId.getValue())
                .orElse(new ArrayList<>());
        log.info("{} 가 요청한 교환 요청 갯수 : {}", memberId.getValue(), findExchangeRequestEntity.size());

        return findExchangeRequestEntity.stream()
                .map(exchangeRequestPersistenceMapper::exchangeEntityToExchange)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ExchangeRequest> findAllByOther(MemberId memberId) {
        // 엔티티 중 요청한 인사이트 ID 속 멤버ID가 == memberId
        List<ExchangeRequestEntity> findExchangeRequestEntity = exchangeRequestJpaRepository.findAllOtherByRequestMemberId(memberId.getValue())
                .orElse(new ArrayList<>());
        log.info("{} 에게 요청한 교환 요청 갯수 : {}", memberId.getValue(), findExchangeRequestEntity.size());

        return findExchangeRequestEntity.stream()
                .map(exchangeRequestPersistenceMapper::exchangeEntityToExchange)
                .collect(Collectors.toList());
    }
}
