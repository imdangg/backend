package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightByAddressQuery;
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
public class ListInsightByAddressCommandHandler {

    private final InsightRepository insightRepository;
    private final InsightDataMapper insightDataMapper;

    @Transactional(readOnly = true)
    public Map<ApartmentComplex, Page<InsightResponse>> listInsightByAddress(ListInsightByAddressQuery listInsightByAddressQuery) {

        // TODO - CHECK
        List<ApartmentComplex> apartmentComplexes = new ArrayList<>();
        Sort sort = Sort.by(Sort.Direction.valueOf(listInsightByAddressQuery.getDirection()), listInsightByAddressQuery.getProperties());
        PageRequest pageRequest = PageRequest.of(listInsightByAddressQuery.getPageNumber(), listInsightByAddressQuery.getPageSize(), sort);

        Map<ApartmentComplex, Page<InsightResponse>> map = new HashMap<>();
        for (ApartmentComplex apartmentComplex : apartmentComplexes) {
            Page<InsightResponse> paged = insightRepository.findAllByApartmentComplex(apartmentComplex, pageRequest)
                    .map(insightDataMapper::insightToInsightResponse);
            map.put(apartmentComplex, paged);
        }
        return map;
    }
}
