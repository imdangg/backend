package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightQuery;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListInsightByMyVisitedApartmentComplexCommandHandler {

    private final InsightRepository insightRepository;
    private final InsightDataMapper insightDataMapper;

    // TODO - 쿼리 개선
    @Transactional(readOnly = true)
    public Map<ApartmentComplex, Page<InsightResponse>> listInsightByMyVisitedApartmentComplex(ListInsightQuery listInsightQuery) {

        // TODO
        List<ApartmentComplex> myVisitedApartmentComplexes = new ArrayList<>();
        Sort sort = Sort.by(Sort.Direction.valueOf(listInsightQuery.getDirection()), listInsightQuery.getProperties());
        PageRequest pageRequest = PageRequest.of(listInsightQuery.getPageNumber(), listInsightQuery.getPageSize(), sort);

        Map<ApartmentComplex, Page<InsightResponse>> map = new HashMap<>();
        for (ApartmentComplex apartmentComplex : myVisitedApartmentComplexes) {
            Page<InsightResponse> paged = insightRepository.findAllByApartmentComplex(apartmentComplex, pageRequest)
                    .map(insightDataMapper::insightToInsightResponse);
            map.put(apartmentComplex, paged);
        }
        return map;
    }
}
