package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightQuery;
import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightResponse;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.exception.InsightNotFoundException;
import com.project.imdang.insight.service.domain.exception.SnapshotNotFoundException;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.service.domain.mapper.SnapshotDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class DetailInsightCommandHandler {

    private final InsightRepository insightRepository;
    private final InsightDataMapper insightDataMapper;
    private final ExchangeRequestRepository exchangeRequestRepository;
    private final SnapshotRepository snapshotRepository;
    private final SnapshotDataMapper snapshotDataMapper;

    // 등록, 수정할 때마다 snapshot 생성
    // 가장 최신 버전의 snapshot 데이터로 보여주기
    // TODO - CHECK : 조회 수 증가
    // 본인의 인사이트, 교환하지 않은 타인의 인사이트, 교환한(수정된/수정 안 된) 타인의 인사이트
    @Transactional(readOnly = true)
    public DetailInsightResponse detailInsight(DetailInsightQuery detailInsightQuery) {

        InsightId insightId = new InsightId(detailInsightQuery.getInsightId());

        Optional<Insight> insightResult = insightRepository.findById(insightId);
        if (insightResult.isPresent()) {
            Insight insight = insightResult.get();

            UUID memberId = detailInsightQuery.getMemberId();
            if (Objects.isNull(memberId)) {
                // TODO : requestedBy == null인 경우(로그인 X)

            } else {

                MemberId requestedBy = new MemberId(memberId);

                // 1. 본인의 인사이트
                if (insight.getMemberId().equals(requestedBy)) {
                    // 교환 신청 여부 확인
                    Optional<ExchangeRequest> optional =
                            exchangeRequestRepository.findByRequestMemberIdAndRequestedInsightId(requestedBy, insightId);
                    ExchangeRequestStatus exchangeRequestStatus = optional.map(ExchangeRequest::getStatus).orElse(null);
                    // TODO - CHECK : OR snapshotRepository
                    return insightDataMapper.insightToDetailInsightResponse(insight, exchangeRequestStatus);
                } else {

                    // 2. 교환 신청 여부 확인
                    Optional<ExchangeRequest> optional =
                            exchangeRequestRepository.findByRequestMemberIdAndRequestedInsightId(requestedBy, insightId);
                    if (optional.isPresent()) {
                        // 2-1. 교환 신청 O
                        ExchangeRequest exchangeRequest = optional.get();

                        if (ExchangeRequestStatus.ACCEPTED.equals(exchangeRequest.getStatus())) {
                            SnapshotId snapshotId = exchangeRequest.getRequestedSnapshotId();
                            Snapshot snapshot = snapshotRepository.findById(snapshotId)
                                    .orElseThrow(() -> new SnapshotNotFoundException(snapshotId));
                            return snapshotDataMapper.snapshotToDetailInsightResponse(snapshot, exchangeRequest.getStatus());
                        } else {
                            // PENDING, REJECTED
                            return insightDataMapper.insightToDetailInsightResponse(insight, exchangeRequest.getStatus())
                                    .toPreviewInsightResponse();
                        }

                    } else {
                        // 2-2. 교환 신청 X
                        return insightDataMapper.insightToDetailInsightResponse(insight, null)
                                .toPreviewInsightResponse();
                    }
                }
            }

        }
        throw new InsightNotFoundException(insightId);
    }
}
