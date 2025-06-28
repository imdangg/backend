package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.dto.insight.detail.DetailInsightQuery;
import com.project.imdang.insight.domain.dto.insight.detail.InsightDetailResult;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.entity.InsightImage;
import com.project.imdang.insight.domain.exception.InsightDomainException;
import com.project.imdang.insight.domain.exception.InsightNotFoundException;
import com.project.imdang.insight.domain.exception.MemberNotFoundException;
import com.project.imdang.insight.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.domain.ports.output.repository.AccuseRepository;
import com.project.imdang.insight.domain.ports.output.repository.InsightImageRepository;
import com.project.imdang.insight.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.domain.ports.output.repository.RecommendRepository;
import com.project.imdang.member.domain.client.MemberData;
import com.project.imdang.member.domain.client.MemberDataResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class DetailInsightQueryHandler {

    private final InsightRepository insightRepository;
    private final InsightImageRepository insightImageRepository;
    private final InsightDataMapper insightDataMapper;

    private final RecommendRepository recommendRepository;
    private final AccuseRepository accuseRepository;

    private final MemberDataResolver memberResolver;

    // TODO - CHECK : 조회 수 증가
    @Transactional(readOnly = true)
    public InsightDetailResult detailInsight(DetailInsightQuery detailInsightQuery) {

        //조회 요청자 정보 얻기
        MemberId requestedBy = detailInsightQuery.getMemberId();
        MemberData requestMember = memberResolver.resolve(requestedBy)
                .orElseThrow(() -> new MemberNotFoundException(requestedBy));

        // 작성한 인사이트가 없거나, 최신 작성 날짜가 한달 이상인 경우
        if (requestMember.getInsightCount() < 1 || requestMember.getLatestInsightCreateDate().isBefore(LocalDate.now().minusDays(30))) {
            throw new InsightDomainException("Latest insight create Date is before one month");
        }

        //인사이트 객체
        InsightId insightId = detailInsightQuery.getInsightId();
        Insight insight = insightRepository.findById(insightId)
                .orElseThrow(() -> new InsightNotFoundException(insightId));
        // 이미지 가져오기
        List<InsightImage> images = insightImageRepository.findByInsightId(insightId);
        //해당 인사이트 추천 여부 검사
        boolean recommended = recommendRepository.findByRecommendMemberIdAndRecommendedInsightId(requestedBy, insightId).isPresent();
        //해당 인사이트 신고 여부 검사
        boolean accused = accuseRepository.findByAccuseMemberIdAndAccusedInsightId(requestedBy, insightId).isPresent();

        //인사이트 작성자 정보 얻기
        MemberId insightCreatedBy = insight.getMemberId();
        MemberData member = memberResolver.resolve(insightCreatedBy)
                .orElseThrow(() -> new MemberNotFoundException(insightCreatedBy));
        String memberNickname = member.getNickname();

        return insightDataMapper.insightToDetailInsightResponse(
                insight, memberNickname, recommended, accused, insightCreatedBy.equals(requestedBy), images);
    }
}
