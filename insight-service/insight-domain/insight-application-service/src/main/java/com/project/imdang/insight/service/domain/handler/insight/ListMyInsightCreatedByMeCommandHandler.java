package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.utils.PagingUtils;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightSimpleResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListMyInsightCreatedByMeQuery;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.entity.MemberSnapshot;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.mapper.SnapshotDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
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
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListMyInsightCreatedByMeCommandHandler {

    private final MemberSnapshotRepository memberSnapshotRepository;
    private final SnapshotRepository snapshotRepository;
    private final SnapshotDataMapper snapshotDataMapper;

    private final InsightRepository insightRepository;

    @Transactional(readOnly = true)
    public Page<InsightSimpleResponse> listMyInsightCreatedByMe(ListMyInsightCreatedByMeQuery listMyInsightCreatedByMeQuery) {

        PageRequest pageRequest = PagingUtils.getPageRequest(
                listMyInsightCreatedByMeQuery.getPageNumber(), listMyInsightCreatedByMeQuery.getPageSize(), listMyInsightCreatedByMeQuery.getDirection(), listMyInsightCreatedByMeQuery.getProperties());

        MemberId memberId = new MemberId(listMyInsightCreatedByMeQuery.getMemberId());
        Page<MemberSnapshot> paged = memberSnapshotRepository.findAllByMemberIdAndSnapshotMemberId(memberId, memberId, pageRequest);

        List<SnapshotId> snapshotIds = paged.getContent().stream()
                .map(MemberSnapshot::getSnapshotId)
                .toList();
        List<Snapshot> snapshots = snapshotRepository.findAllByIds(snapshotIds);
        List<InsightSimpleResponse> insightSimpleResponses = getInsightResponses(snapshots);
        return new PageImpl<>(insightSimpleResponses, paged.getPageable(), paged.getTotalElements());
    }

    private List<InsightSimpleResponse> getInsightResponses(List<Snapshot> snapshots) {
        Map<InsightId, Integer> recommendedCountMap = getRecommendedCountMap(snapshots);
        return snapshots.stream()
                .map(snapshot -> {
                    Integer recommendedCount = recommendedCountMap.get(snapshot.getInsightId());
                    return snapshotDataMapper.snapshotToInsightSimpleResponse(snapshot, recommendedCount);
                }).toList();
    }

    private Map<InsightId, Integer> getRecommendedCountMap(List<Snapshot> snapshots) {
        List<InsightId> insightIds = snapshots.stream()
                .map(Snapshot::getInsightId)
                .toList();
        return insightRepository.findAllByIds(insightIds).stream()
                .collect(Collectors.toMap(Insight::getId, Insight::getRecommendedCount));
    }
}
