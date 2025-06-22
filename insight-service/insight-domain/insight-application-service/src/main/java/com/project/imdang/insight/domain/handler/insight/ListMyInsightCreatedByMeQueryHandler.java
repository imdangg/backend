package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.utils.PagingUtils;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.dto.insight.list.InsightResult;
import com.project.imdang.insight.domain.dto.insight.list.InsightSimpleResult;
import com.project.imdang.insight.domain.dto.insight.list.ListMyInsightCreatedByMeQuery;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.mapper.InsightDataMapper;
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
public class ListMyInsightCreatedByMeQueryHandler {

    private final InsightRepository insightRepository;
    private final InsightDataMapper insightDataMapper;
    private final MemberDataResolver memberResolver;

    @Transactional(readOnly = true)
    public Page<InsightResult> listMyInsightCreatedByMe(ListMyInsightCreatedByMeQuery listMyInsightCreatedByMeQuery) {

        PageRequest pageRequest = PagingUtils.getPageRequest(
                listMyInsightCreatedByMeQuery.getPageNumber(), listMyInsightCreatedByMeQuery.getPageSize(), listMyInsightCreatedByMeQuery.getDirection(), listMyInsightCreatedByMeQuery.getProperties());
        MemberId memberId = listMyInsightCreatedByMeQuery.getMemberId();

        //내가 작성한 인사이트 목록 조회
        Page<Insight> insightPage = insightRepository.findAllByMemberId(memberId, pageRequest);
        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(insightPage.getContent());

        return insightPage.map(insight -> {
            String memberNickname = memberNicknameMap.get(insight.getMemberId());
            return insightDataMapper.insightToInsightResponse(insight, memberNickname);
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
