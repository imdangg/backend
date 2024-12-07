package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.insight.service.domain.dto.insight.list.ListMyInsightByAddressQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.SnapshotResponse;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListMyInsightByAddressCommandHandler {

    private final MemberSnapshotRepository memberSnapshotRepository;
//    private final InsightRepository insightRepository;
    private final SnapshotRepository snapshotRepository;
    private final InsightDataMapper insightDataMapper;

    // TODO - ASK : 분리
    // TODO - CHECK : 본인이 작성한 인사이트는 X
    @Transactional(readOnly = true)
    public Map<ApartmentComplex, Page<SnapshotResponse>> listMyInsightByAddress(ListMyInsightByAddressQuery listMyInsightByAddressQuery) {
        return null;
    }
}
