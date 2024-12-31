package com.project.imdang.insight.service.persistence.insight.mapper;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.MemberSnapshot;
import com.project.imdang.insight.service.domain.valueobject.MemberSnapshotId;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
import com.project.imdang.insight.service.persistence.insight.entity.MemberSnapshotEntity;
import org.springframework.stereotype.Component;

@Component
public class MemberSnapshotPersistenceMapper {

    public MemberSnapshotEntity memberSnapshotToMemberSnapshotEntity(MemberSnapshot memberSnapshot) {
        return MemberSnapshotEntity.builder()
                .id(memberSnapshot.getId() != null ?
                        memberSnapshot.getId().getValue() : null)
                .memberId(memberSnapshot.getMemberId().getValue())
                .snapshotId(memberSnapshot.getSnapshotId().getValue())
                .insightId(memberSnapshot.getInsightId().getValue())
                .createdAt(memberSnapshot.getCreatedAt())
                .build();
    }

    public MemberSnapshot memberSnapshotEntityToMemberSnapshot(MemberSnapshotEntity memberSnapshotEntity) {
        return MemberSnapshot.builder()
                .id(new MemberSnapshotId(memberSnapshotEntity.getId()))
                .memberId(new MemberId(memberSnapshotEntity.getMemberId()))
                .snapshotId(new SnapshotId(memberSnapshotEntity.getSnapshotId()))
                .insightId(new InsightId(memberSnapshotEntity.getInsightId()))
                .createdAt(memberSnapshotEntity.getCreatedAt())
                .build();
    }
}
