package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.utils.PagingUtils;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListMyInsightQuery;
import com.project.imdang.insight.service.domain.entity.MemberSnapshot;
import com.project.imdang.insight.service.domain.mapper.SnapshotDataMapper;
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

@Slf4j
@RequiredArgsConstructor
@Component
public class ListMyInsightCommandHandler {

    private final MemberSnapshotRepository memberSnapshotRepository;
    private final SnapshotRepository snapshotRepository;
    private final SnapshotDataMapper snapshotDataMapper;

    @Transactional(readOnly = true)
    public Page<InsightResponse> listMyInsight(ListMyInsightQuery listMyInsightQuery) {

        PageRequest pageRequest = PagingUtils.getPageRequest(
                listMyInsightQuery.getPageNumber(), listMyInsightQuery.getPageSize(), listMyInsightQuery.getDirection(), listMyInsightQuery.getProperties());
        MemberId memberId = new MemberId(listMyInsightQuery.getMemberId());

        // TODO - CHECK : EntityGraph
        Page<MemberSnapshot> paged
                = memberSnapshotRepository.findAllByMemberId(memberId, pageRequest);
        List<SnapshotId> snapshotIds = paged.getContent().stream()
                .map(MemberSnapshot::getSnapshotId)
                .toList();
        List<InsightResponse> insightResponses = snapshotRepository.findAllByIds(snapshotIds).stream()
                .map(snapshotDataMapper::snapshotToInsightResponse)
                .toList();
        return new PageImpl<>(insightResponses, paged.getPageable(), paged.getTotalElements());
    }
}
