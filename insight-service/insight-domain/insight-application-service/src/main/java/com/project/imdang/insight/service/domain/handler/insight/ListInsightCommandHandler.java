package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightQuery;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListInsightCommandHandler {

    private final InsightRepository insightRepository;
    private final InsightDataMapper insightDataMapper;

    @Transactional(readOnly = true)
    public Page<InsightResponse> listInsight(ListInsightQuery listInsightQuery) {
        Sort sort = Sort.by(Sort.Direction.valueOf(listInsightQuery.getDirection()), listInsightQuery.getProperties());
        PageRequest pageRequest = PageRequest.of(listInsightQuery.getPageNumber(), listInsightQuery.getPageSize(), sort);
        return insightRepository.findAll(pageRequest)
                .map(insightDataMapper::insightToInsightResponse);
    }
}
