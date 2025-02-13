package com.project.imdang.insight.service.persistence.adapter;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
import com.project.imdang.insight.service.persistence.entity.ExchangeRequestEntity;
import com.project.imdang.insight.service.persistence.mapper.ExchangeRequestPersistenceMapper;
import com.project.imdang.insight.service.persistence.repository.ExchangeRequestJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
        ExchangeRequestEntity exchangeRequestEntity = exchangeRequestPersistenceMapper.exchangeRequestToExchangeRequestEntity(exchangeRequest);
        ExchangeRequestEntity saved = exchangeRequestJpaRepository.save(exchangeRequestEntity);
        return exchangeRequestPersistenceMapper.exchangeRequestEntityToExchangeRequest(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ExchangeRequest> findById(ExchangeRequestId exchangeRequestId) {
        return exchangeRequestJpaRepository.findById(exchangeRequestId.getValue())
                .map(exchangeRequestPersistenceMapper::exchangeRequestEntityToExchangeRequest);
    }

    @Override
    public Optional<ExchangeRequest> findByRequestMemberIdAndRequestedInsightId(MemberId requestMemberId, InsightId requestedInsightId) {
        return exchangeRequestJpaRepository.findByRequestMemberIdAndRequestedInsightId(requestMemberId.getValue(), requestedInsightId.getValue())
                .map(exchangeRequestPersistenceMapper::exchangeRequestEntityToExchangeRequest);
    }

    @Override
    public Optional<ExchangeRequest> findByRequestedMemberIdAndMemberCouponIdAndRequestMemberInsightId(MemberId requestedMemberId, MemberCouponId memberCouponId, InsightId requestMemberInsightId) {
        return exchangeRequestJpaRepository.findByRequestedMemberIdAndMemberCouponIdAndRequestMemberInsightId(
                        requestedMemberId.getValue(),
                        memberCouponId != null ? memberCouponId.getValue() : null,
                        requestMemberInsightId != null ? requestMemberInsightId.getValue() : null)
                .map(exchangeRequestPersistenceMapper::exchangeRequestEntityToExchangeRequest);
    }

    @Override
    public Optional<ExchangeRequest> findByRequestMemberInsightIdAndRequestedInsightId(InsightId requestMemberInsightId, InsightId requestedInsightId) {
        return exchangeRequestJpaRepository.findByRequestMemberInsightIdAndRequestedInsightId(requestMemberInsightId.getValue(), requestedInsightId.getValue())
                .map(exchangeRequestPersistenceMapper::exchangeRequestEntityToExchangeRequest);
    }

    @Override
    public Page<ExchangeRequest> findAllByRequestMemberIdAndExchangeRequestStatus(MemberId requestMemberId, ExchangeRequestStatus exchangeRequestStatus, PageRequest pageRequest) {
        return exchangeRequestJpaRepository.findAllByRequestMemberIdAndStatus(requestMemberId.getValue(), exchangeRequestStatus, pageRequest)
                .map(exchangeRequestPersistenceMapper::exchangeRequestEntityToExchangeRequest);
    }

    @Override
    public Page<ExchangeRequest> findAllByRequestedMemberIdAndExchangeRequestStatus(MemberId requestedMemberId, ExchangeRequestStatus exchangeRequestStatus, PageRequest pageRequest) {
        return exchangeRequestJpaRepository.findAllByRequestedMemberIdAndStatus(requestedMemberId.getValue(), exchangeRequestStatus, pageRequest)
                .map(exchangeRequestPersistenceMapper::exchangeRequestEntityToExchangeRequest);
    }
}
