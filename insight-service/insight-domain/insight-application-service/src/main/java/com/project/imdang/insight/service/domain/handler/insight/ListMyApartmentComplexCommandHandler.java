package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
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
public class ListMyApartmentComplexCommandHandler {

    private final MemberSnapshotRepository memberSnapshotRepository;

    // 보관중인 인사이트 단지 목록
    @Transactional(readOnly = true)
    public List<ApartmentComplex> listMyApartmentComplex(UUID _memberId) {
        MemberId memberId = new MemberId(_memberId);
        return memberSnapshotRepository.findAllDistinctApartmentComplexByMemberId(memberId);
    }
}
