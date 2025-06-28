package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.utils.PagingUtils;
import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.dto.insight.list.InsightResult;
import com.project.imdang.insight.domain.dto.insight.list.ListBookmarkedInsightCreatedByMeQuery;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.entity.InsightImage;
import com.project.imdang.insight.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.domain.ports.output.repository.InsightImageRepository;
import com.project.imdang.insight.domain.ports.output.repository.InsightRepository;
import com.project.imdang.member.domain.client.MemberData;
import com.project.imdang.member.domain.client.MemberDataResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListBookmarkedInsightCreatedByMeQueryHandler {

    private final InsightRepository insightRepository;
    private final InsightImageRepository insightImageRepository;
    private final InsightDataMapper insightDataMapper;
    private final MemberDataResolver memberResolver;

    @Transactional(readOnly = true)
    public Page<InsightResult> listBookmarkedInsightCreatedByMe(ListBookmarkedInsightCreatedByMeQuery listBookmarkedInsightCreatedByMeQuery) {

        PageRequest pageRequest = PagingUtils.getPageRequest(
                listBookmarkedInsightCreatedByMeQuery.getPageNumber(), listBookmarkedInsightCreatedByMeQuery.getPageSize(), listBookmarkedInsightCreatedByMeQuery.getDirection(), listBookmarkedInsightCreatedByMeQuery.getProperties());
        MemberId memberId = listBookmarkedInsightCreatedByMeQuery.getMemberId();

        //내가 작성한 인사이트 목록 조회
        Page<Insight> insightPage = insightRepository.findAllByMemberId(memberId, pageRequest);
        List<Insight> insights = insightPage.getContent();
        List<InsightId> insightIds = insights.stream()
                .map(Insight::getId)
                .toList();

        // 이미지 조회 및 맵핑
        List<InsightImage> images = insightImageRepository.findByInsightIdIn(insightIds);
        Map<InsightId, List<InsightImage>> imageMap = images.stream()
                .collect(Collectors.groupingBy(InsightImage::getInsightId));
        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(insightPage.getContent());

        return insightPage.map(insight -> {
            List<InsightImage> matchedImages = imageMap.getOrDefault(insight.getId(), List.of());
            String nickname = memberNicknameMap.get(insight.getMemberId());
            return insightDataMapper.insightToInsightResponse(insight, nickname, matchedImages);
        });
    }

    //인사이트별 작성자 닉네임 조립
    private Map<MemberId, String> getMemberNicknameMap(List<Insight> insights) {
        List<MemberId> memberIds = insights.stream()
                .map(Insight::getMemberId)
                .toList();
        return memberResolver.resolve(memberIds).stream()
                .collect(Collectors.toMap(memberData -> new MemberId(memberData.getMemberId()), MemberData::getNickname));
    }
}
