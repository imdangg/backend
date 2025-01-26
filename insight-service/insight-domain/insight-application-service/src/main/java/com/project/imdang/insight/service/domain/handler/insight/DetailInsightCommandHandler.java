package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightQuery;
import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightResponse;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.exception.InsightApplicationServiceException;
import com.project.imdang.insight.service.domain.exception.InsightNotFoundException;
import com.project.imdang.insight.service.domain.exception.SnapshotNotFoundException;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.service.domain.mapper.SnapshotDataMapper;
import com.project.imdang.insight.service.domain.ports.output.lookup.InsightMemberLookup;
import com.project.imdang.insight.service.domain.ports.output.repository.AccuseRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.RecommendRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
import com.project.imdang.insight.service.domain.valueobject.MemberInfo;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.project.imdang.domain.exception.ErrorCode.MEMBER_NOT_EXIST;

@Slf4j
@RequiredArgsConstructor
@Component
public class DetailInsightCommandHandler {

    private final InsightRepository insightRepository;
    private final InsightDataMapper insightDataMapper;
    private final ExchangeRequestRepository exchangeRequestRepository;
    private final SnapshotRepository snapshotRepository;
    private final SnapshotDataMapper snapshotDataMapper;

    private final RecommendRepository recommendRepository;
    private final AccuseRepository accuseRepository;

    private final InsightMemberLookup insightMemberLookup;

    // PENDING + 누가 요청한 것인가?
    // 교환 요청을 받았으나 아직 수락하지 않은 상태
    // 교환 요청을 했으나 상대방이 아직 수락하지 않은 상태

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
                boolean recommended = recommendRepository.findByRecommendMemberIdAndRecommendedInsightId(requestedBy, insightId).isPresent();
                boolean accused = accuseRepository.findByAccuseMemberIdAndAccusedInsightId(requestedBy, insightId).isPresent();
                MemberInfo memberInfo = insightMemberLookup.lookupByMemberId(requestedBy)
                        .orElseThrow(() -> new InsightApplicationServiceException(MEMBER_NOT_EXIST));
                String memberNickname = memberInfo.nickname();

                // 1. 본인의 인사이트
                if (insight.getMemberId().equals(requestedBy)) {

                    // 교환 신청 여부 확인
                    Optional<ExchangeRequest> optional =
                            exchangeRequestRepository.findByRequestedMemberIdAndRequestedInsightId(requestedBy, insightId);
                    if (optional.isPresent()) {
                        ExchangeRequest exchangeRequest = optional.get();
                        ExchangeRequestStatus exchangeRequestStatus = exchangeRequest.getStatus();
                        Boolean exchangeRequestCreatedByMe = exchangeRequest.getRequestMemberId().equals(requestedBy);
                        ExchangeRequestId exchangeRequestId = exchangeRequest.getId();
                        // TODO - CHECK : OR snapshotRepository
                        return insightDataMapper.insightToDetailInsightResponse(insight, memberNickname, recommended, accused, exchangeRequestStatus, exchangeRequestCreatedByMe, exchangeRequestId);
                    } else {
                        return insightDataMapper.insightToDetailInsightResponse(insight, memberNickname, recommended, accused, null, null, null);
                    }

                } else {

                    // 2. 교환 신청 여부 확인
                    Optional<ExchangeRequest> optional =
                            exchangeRequestRepository.findByRequestMemberIdAndRequestedInsightId(requestedBy, insightId);
                    if (optional.isPresent()) {

                        // 2-1. 교환 신청 O
                        ExchangeRequest exchangeRequest = optional.get();
                        ExchangeRequestStatus exchangeRequestStatus = exchangeRequest.getStatus();
                        Boolean exchangeRequestCreatedByMe = exchangeRequest.getRequestMemberId().equals(requestedBy);
                        ExchangeRequestId exchangeRequestId = exchangeRequest.getId();

                        if (ExchangeRequestStatus.ACCEPTED.equals(exchangeRequestStatus)) {
                            SnapshotId snapshotId = exchangeRequest.getRequestedSnapshotId();
                            Snapshot snapshot = snapshotRepository.findById(snapshotId)
                                    .orElseThrow(() -> new SnapshotNotFoundException(snapshotId));
                            Integer recommendedCount = insight.getRecommendedCount();
                            Integer accusedCount = insight.getAccusedCount();
                            Integer viewCount = insight.getViewCount();
                            return snapshotDataMapper.snapshotToDetailInsightResponse(snapshot, memberNickname, recommended, accused, recommendedCount, accusedCount, viewCount, exchangeRequestStatus, exchangeRequestCreatedByMe, exchangeRequestId);
                        } else {
                            // PENDING, REJECTED - 교환 완료해야 추천 가능
                            return insightDataMapper.insightToDetailInsightResponse(insight, memberNickname, recommended, accused, exchangeRequestStatus, exchangeRequestCreatedByMe, exchangeRequestId)
                                    .toPreviewInsightResponse();
                        }

                    } else {
                        // 2-2. 교환 신청 X - 교환 완료해야 추천 가능
                        return insightDataMapper.insightToDetailInsightResponse(insight, memberNickname, recommended, accused, null, null, null)
                                .toPreviewInsightResponse();
                    }
                }
            }

        }
        throw new InsightNotFoundException(insightId);
    }
}
