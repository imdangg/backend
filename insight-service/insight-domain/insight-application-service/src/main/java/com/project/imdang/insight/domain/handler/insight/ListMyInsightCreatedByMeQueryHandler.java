package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.utils.PagingUtils;
import com.project.imdang.insight.domain.dto.insight.list.InsightSimpleResult;
import com.project.imdang.insight.domain.dto.insight.list.ListMyInsightCreatedByMeQuery;
import com.project.imdang.insight.domain.ports.output.repository.InsightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListMyInsightCreatedByMeQueryHandler {

    private final InsightRepository insightRepository;

    @Transactional(readOnly = true)
    public Page<InsightSimpleResult> listMyInsightCreatedByMe(ListMyInsightCreatedByMeQuery listMyInsightCreatedByMeQuery) {

        PageRequest pageRequest = PagingUtils.getPageRequest(
                listMyInsightCreatedByMeQuery.getPageNumber(), listMyInsightCreatedByMeQuery.getPageSize(), listMyInsightCreatedByMeQuery.getDirection(), listMyInsightCreatedByMeQuery.getProperties());
        return null;
    }
}
