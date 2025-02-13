package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface ExchangeRequestRepository {

    ExchangeRequest save(ExchangeRequest exchangeRequest);
    Optional<ExchangeRequest> findById(ExchangeRequestId exchangeRequestId);

    // 교환 요청한 사람 - 교환 요청받은 인사이트
    Optional<ExchangeRequest> findByRequestMemberIdAndRequestedInsightId(MemberId requestMemberId, InsightId requestedInsightId);
    // 교환 요청받은 사람 - 교환 요청 시 사용된 MemberCoupon - 교환 요청 시 사용된 인사이트
    Optional<ExchangeRequest> findByRequestedMemberIdAndMemberCouponIdAndRequestMemberInsightId(MemberId requestedMemberId, MemberCouponId memberCouponId, InsightId requestMemberInsightId);
    // 교환 요청한 사람 - 교환 요청받은 인사이트
    Optional<ExchangeRequest> findByRequestMemberInsightIdAndRequestedInsightId(InsightId requestMemberInsightId, InsightId requestedInsightId);

    Page<ExchangeRequest> findAllByRequestMemberIdAndExchangeRequestStatus(MemberId requestMemberId, ExchangeRequestStatus exchangeRequestStatus, PageRequest pageRequest);
    Page<ExchangeRequest> findAllByRequestedMemberIdAndExchangeRequestStatus(MemberId requestedMemberId, ExchangeRequestStatus exchangeRequestStatus, PageRequest pageRequest);
}
