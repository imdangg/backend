package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.ports.output.repository.InsightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListMyVisitedApartmentComplexQueryHandler {

    private final InsightRepository insightRepository;

    @Transactional(readOnly = true)
    public List<ApartmentComplex> listMyVisitedApartmentComplex(MemberId memberId) {
        return insightRepository.findDistinctApartmentComplexByMemberId(memberId);
    }
}
