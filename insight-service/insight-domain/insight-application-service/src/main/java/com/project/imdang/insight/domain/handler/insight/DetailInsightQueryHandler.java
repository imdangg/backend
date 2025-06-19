package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.dto.insight.detail.DetailInsightQuery;
import com.project.imdang.insight.domain.dto.insight.detail.InsightDetailResult;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.exception.InsightNotFoundException;
import com.project.imdang.insight.domain.exception.MemberNotFoundException;
import com.project.imdang.insight.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.domain.ports.output.repository.AccuseRepository;
import com.project.imdang.insight.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.domain.ports.output.repository.RecommendRepository;
import com.project.imdang.member.domain.client.MemberData;
import com.project.imdang.member.domain.client.MemberDataResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class DetailInsightQueryHandler {

    private final InsightRepository insightRepository;
    private final InsightDataMapper insightDataMapper;

    private final RecommendRepository recommendRepository;
    private final AccuseRepository accuseRepository;

    private final MemberDataResolver memberResolver;

    // TODO - CHECK : 조회 수 증가
    @Transactional(readOnly = true)
    public InsightDetailResult detailInsight(DetailInsightQuery detailInsightQuery) {

        InsightId insightId = detailInsightQuery.getInsightId();
        Insight insight = insightRepository.findById(insightId)
                .orElseThrow(() -> new InsightNotFoundException(insightId));

        MemberId requestedBy = detailInsightQuery.getMemberId();
        boolean recommended = recommendRepository.findByRecommendMemberIdAndRecommendedInsightId(requestedBy, insightId).isPresent();
        boolean accused = accuseRepository.findByAccuseMemberIdAndAccusedInsightId(requestedBy, insightId).isPresent();
        MemberId insightCreatedBy = insight.getMemberId();
        MemberData member = memberResolver.resolve(insightCreatedBy)
                .orElseThrow(() -> new MemberNotFoundException(insightCreatedBy));
        String memberNickname = member.getNickname();
        return insightDataMapper.insightToDetailInsightResponse(
                insight, memberNickname, recommended, accused, insightCreatedBy.equals(requestedBy));
    }
}
