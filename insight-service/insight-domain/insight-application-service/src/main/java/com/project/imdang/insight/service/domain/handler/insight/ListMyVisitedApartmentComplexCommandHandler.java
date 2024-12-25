package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListMyVisitedApartmentComplexCommandHandler {

    private final InsightRepository insightRepository;

    @Transactional(readOnly = true)
    public List<ApartmentComplex> listMyVisitedApartmentComplex(UUID _memberId) {
        MemberId memberId = new MemberId(_memberId);
        return insightRepository.findDistinctApartmentComplexByMemberId(memberId);
    }
}
