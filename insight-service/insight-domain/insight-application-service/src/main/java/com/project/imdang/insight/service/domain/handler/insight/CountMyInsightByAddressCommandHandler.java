package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.insight.list.MyInsightResponse;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import com.project.imdang.insight.service.domain.valueobject.Address;
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
public class CountMyInsightByAddressCommandHandler {

    private final MemberSnapshotRepository memberSnapshotRepository;

    @Transactional(readOnly = true)
    public MyInsightResponse countMyInsightByAddress(UUID _memberId, Address address) {
        MemberId memberId = new MemberId(_memberId);
        List<ApartmentComplex> apartmentComplexes = memberSnapshotRepository.findAllDistinctApartmentComplexByMemberIdAndAddress(memberId, address);
        int insightCount = memberSnapshotRepository.countAllByMemberIdAndAddress(memberId, address);
        return MyInsightResponse.builder()
                .apartmentComplexes(apartmentComplexes)
                .insightCount(insightCount)
                .build();
    }
}
