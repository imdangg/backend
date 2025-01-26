package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.utils.PagingUtils;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListMyInsightQuery;
import com.project.imdang.insight.service.domain.entity.MemberSnapshot;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.mapper.SnapshotDataMapper;
import com.project.imdang.insight.service.domain.ports.output.lookup.InsightMemberLookup;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.MemberInfo;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListMyInsightCommandHandler {

    private final MemberSnapshotRepository memberSnapshotRepository;
    private final SnapshotRepository snapshotRepository;
    private final SnapshotDataMapper snapshotDataMapper;

    private final InsightMemberLookup insightMemberLookup;

    @Transactional(readOnly = true)
    public Page<InsightResponse> listMyInsight(ListMyInsightQuery listMyInsightQuery) {

        PageRequest pageRequest = PagingUtils.getPageRequest(
                listMyInsightQuery.getPageNumber(), listMyInsightQuery.getPageSize(), listMyInsightQuery.getDirection(), listMyInsightQuery.getProperties());
        MemberId memberId = new MemberId(listMyInsightQuery.getMemberId());
        Boolean onlyMine = listMyInsightQuery.getOnlyMine();

        // TODO - CHECK : EntityGraph
        Page<MemberSnapshot> paged = null;
        if (listMyInsightQuery.getApartmentComplexName() != null) {
            // 단지별 보기
            ApartmentComplex apartmentComplex
                    = new ApartmentComplex(listMyInsightQuery.getApartmentComplexName());

            if (Boolean.TRUE.equals(onlyMine)) {
                paged = memberSnapshotRepository.findAllByMemberIdAndApartmentComplexAndSnapshotMemberId(memberId, apartmentComplex, memberId, pageRequest);
            } else {
                paged = memberSnapshotRepository.findAllByMemberIdAndApartmentComplex(memberId, apartmentComplex, pageRequest);
            }
        } else {

            if (Boolean.TRUE.equals(onlyMine)) {
                paged = memberSnapshotRepository.findAllByMemberIdAndDistrictAndSnapshotMemberId(memberId, listMyInsightQuery.getDistrict(), memberId, pageRequest);
            } else {
                paged = memberSnapshotRepository.findAllByMemberIdAndDistrict(memberId, listMyInsightQuery.getDistrict(), pageRequest);
            }
        }

        List<SnapshotId> snapshotIds = paged.getContent().stream()
                .map(MemberSnapshot::getSnapshotId)
                .toList();
        List<Snapshot> snapshots = snapshotRepository.findAllByIds(snapshotIds);
        List<InsightResponse> insightResponses = getInsightResponses(snapshots);
        return new PageImpl<>(insightResponses, paged.getPageable(), paged.getTotalElements());
    }

    private List<InsightResponse> getInsightResponses(List<Snapshot> snapshots) {
        Map<UUID, String> memberNicknameMap = getMemberNicknameMap(snapshots);
        return snapshots.stream().map(snapshot -> {
            String memberNickname = memberNicknameMap.get(snapshot.getMemberId().getValue());
            return snapshotDataMapper.snapshotToInsightResponse(snapshot, memberNickname);
        }).toList();
    }

    private Map<UUID, String> getMemberNicknameMap(List<Snapshot> snapshots) {
        List<MemberId> memberIds = snapshots.stream()
                .map(Snapshot::getMemberId)
                .toList();
        return insightMemberLookup.lookupByMemberIds(memberIds).stream()
                .collect(Collectors.toMap(MemberInfo::memberId, MemberInfo::nickname));
    }
}
