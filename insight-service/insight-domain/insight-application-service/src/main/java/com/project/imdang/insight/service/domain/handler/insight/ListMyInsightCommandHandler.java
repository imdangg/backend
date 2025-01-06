package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.utils.PagingUtils;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListMyInsightQuery;
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
public class ListMyInsightCommandHandler {

    private final MemberSnapshotRepository memberSnapshotRepository;
    private final SnapshotRepository snapshotRepository;
    private final SnapshotDataMapper snapshotDataMapper;

    // 내 인사이트 + 교환한 인사이트
    // + 단지별 보기
    @Transactional(readOnly = true)
    public Page<InsightResponse> listMyInsight(ListMyInsightQuery listMyInsightQuery) {

        PageRequest pageRequest = PagingUtils.getPageRequest(
                listMyInsightQuery.getPageNumber(), listMyInsightQuery.getPageSize(), listMyInsightQuery.getDirection(), listMyInsightQuery.getProperties());
        MemberId memberId = new MemberId(listMyInsightQuery.getMemberId());
        Boolean onlyMine = listMyInsightQuery.getOnlyMine();

        // TODO - CHECK : EntityGraph
        Page<MemberSnapshot> paged = null;
        if (listMyInsightQuery.getApartmentComplexKey() != null) {
            // 단지별 보기
            ApartmentComplex apartmentComplex
                    = new ApartmentComplex(listMyInsightQuery.getApartmentComplexName(), listMyInsightQuery.getApartmentComplexKey());

            if (Boolean.TRUE.equals(onlyMine)) {
                paged = memberSnapshotRepository.findAllByMemberIdAndApartmentComplexAndSnapshotMemberId(memberId, apartmentComplex, memberId, pageRequest);
            } else {
                paged = memberSnapshotRepository.findAllByMemberIdAndApartmentComplex(memberId, apartmentComplex, pageRequest);
            }
        } else {

            if (Boolean.TRUE.equals(onlyMine)) {
                paged = memberSnapshotRepository.findAllByMemberIdAndSnapshotMemberId(memberId, memberId, pageRequest);
            } else {
                paged = memberSnapshotRepository.findAllByMemberId(memberId, pageRequest);
            }
        }

        List<InsightResponse> insightResponses = getInsightResponses(paged);
        return new PageImpl<>(insightResponses, paged.getPageable(), paged.getTotalElements());
    }

    private List<InsightResponse> getInsightResponses(Page<MemberSnapshot> paged) {
        List<SnapshotId> snapshotIds = paged.getContent().stream()
                .map(MemberSnapshot::getSnapshotId)
                .toList();
        return snapshotRepository.findAllByIds(snapshotIds).stream()
                .map(snapshotDataMapper::snapshotToInsightResponse)
                .toList();
    }
}
