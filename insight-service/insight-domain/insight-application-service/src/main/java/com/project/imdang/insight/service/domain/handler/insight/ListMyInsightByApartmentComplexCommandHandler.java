package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.utils.PagingUtils;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListMyInsightByApartmentComplexQuery;
import com.project.imdang.insight.service.domain.entity.MemberSnapshot;
import com.project.imdang.insight.service.domain.mapper.SnapshotDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListMyInsightByApartmentComplexCommandHandler {

    private final MemberSnapshotRepository memberSnapshotRepository;
    private final SnapshotRepository snapshotRepository;
    private final SnapshotDataMapper snapshotDataMapper;

    // 단지별 보기 (내 인사이트 + 교환한 인사이트)
    @Transactional(readOnly = true)
    public Page<InsightResponse> listMyInsightByApartmentComplex(ListMyInsightByApartmentComplexQuery listMyInsightByApartmentComplexQuery) {

        PageRequest pageRequest = PagingUtils.getPageRequest(
                listMyInsightByApartmentComplexQuery.getPageNumber(),
                listMyInsightByApartmentComplexQuery.getPageSize(),
                listMyInsightByApartmentComplexQuery.getDirection(),
                listMyInsightByApartmentComplexQuery.getProperties());
        MemberId memberId = new MemberId(listMyInsightByApartmentComplexQuery.getMemberId());
        ApartmentComplex apartmentComplex = new ApartmentComplex(listMyInsightByApartmentComplexQuery.getApartmentComplexName(), listMyInsightByApartmentComplexQuery.getApartmentComplexKey());

        // TODO - CHECK : EntityGraph
        Page<MemberSnapshot> paged
                = memberSnapshotRepository.findAllByMemberIdAndApartmentComplex(memberId, apartmentComplex, pageRequest);
        List<SnapshotId> snapshotIds = paged.getContent().stream()
                .map(MemberSnapshot::getSnapshotId)
                .toList();
        List<InsightResponse> insightResponses = snapshotRepository.findAllByIds(snapshotIds).stream()
                .map(snapshotDataMapper::snapshotToInsightResponse)
                .toList();
        return new PageImpl<>(insightResponses, paged.getPageable(), paged.getTotalElements());
    }
}
