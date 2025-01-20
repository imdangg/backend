package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.utils.PagingUtils;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightSimpleResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListMyInsightCreatedByMeQuery;
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
public class ListMyInsightCreatedByMeCommandHandler {

    private final MemberSnapshotRepository memberSnapshotRepository;
    private final SnapshotRepository snapshotRepository;
    private final SnapshotDataMapper snapshotDataMapper;

    @Transactional(readOnly = true)
    public Page<InsightSimpleResponse> listMyInsightCreatedByMe(ListMyInsightCreatedByMeQuery listMyInsightCreatedByMeQuery) {

        PageRequest pageRequest = PagingUtils.getPageRequest(
                listMyInsightCreatedByMeQuery.getPageNumber(), listMyInsightCreatedByMeQuery.getPageSize(), listMyInsightCreatedByMeQuery.getDirection(), listMyInsightCreatedByMeQuery.getProperties());

        MemberId memberId = new MemberId(listMyInsightCreatedByMeQuery.getMemberId());
        Page<MemberSnapshot> paged = memberSnapshotRepository.findAllByMemberIdAndSnapshotMemberId(memberId, memberId, pageRequest);

        List<InsightSimpleResponse> insightSimpleResponses = getInsightResponses(paged);
        return new PageImpl<>(insightSimpleResponses, paged.getPageable(), paged.getTotalElements());
    }

    private List<InsightSimpleResponse> getInsightResponses(Page<MemberSnapshot> paged) {
        List<SnapshotId> snapshotIds = paged.getContent().stream()
                .map(MemberSnapshot::getSnapshotId)
                .toList();
        return snapshotRepository.findAllByIds(snapshotIds).stream()
                .map(snapshotDataMapper::snapshotToInsightSimpleResponse)
                .toList();
    }
}
