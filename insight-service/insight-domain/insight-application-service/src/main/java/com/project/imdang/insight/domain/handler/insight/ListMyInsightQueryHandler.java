package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.utils.PagingUtils;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.dto.insight.list.InsightResult;
import com.project.imdang.insight.domain.dto.insight.list.ListMyInsightQuery;
import com.project.imdang.insight.domain.ports.output.repository.InsightRepository;
import com.project.imdang.member.domain.client.MemberDataResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListMyInsightQueryHandler {

    private final InsightRepository insightRepository;
    private final MemberDataResolver memberResolver;

    @Transactional(readOnly = true)
    public Page<InsightResult> listMyInsight(ListMyInsightQuery listMyInsightQuery) {

        PageRequest pageRequest = PagingUtils.getPageRequest(
                listMyInsightQuery.getPageNumber(), listMyInsightQuery.getPageSize(), listMyInsightQuery.getDirection(), listMyInsightQuery.getProperties());
        MemberId memberId = listMyInsightQuery.getMemberId();
        Boolean onlyMine = listMyInsightQuery.getOnlyMine();
        return null;
    }
}
