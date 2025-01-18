package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import com.project.imdang.insight.service.domain.valueobject.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListMyInsightAddressCommandHandler {

    private final MemberSnapshotRepository memberSnapshotRepository;

    @Transactional(readOnly = true)
    public List<Address> listMyInsightAddress(UUID _memberId) {
        MemberId memberId = new MemberId(_memberId);
        return memberSnapshotRepository.findAllDistinctAddressByMemberId(memberId);
    }
}
