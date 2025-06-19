package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.valueobject.District;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.dto.insight.list.MyApartmentComplexResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListMyInsightApartmentComplexByDistrictQueryHandler {

    @Transactional(readOnly = true)
    public List<MyApartmentComplexResult> listMyInsightApartmentComplexByDistrict(MemberId memberId, District district) {
        List<Object[]> results = new ArrayList<>();
        return results.stream()
                .map(result -> MyApartmentComplexResult.builder()
                        .apartmentComplexName((String) result[0])
                        .insightCount((Long) result[1])
                        .build())
                .collect(Collectors.toList());
    }
}
