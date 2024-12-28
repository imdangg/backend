package com.project.imdang.insight.service.domain.entity;


import com.project.imdang.domain.entity.BaseEntity;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.valueobject.MemberSnapshotId;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class MemberSnapshot extends BaseEntity<MemberSnapshotId> {

    private final MemberId memberId;
    private final SnapshotId snapshotId;
    private final InsightId insightId;
    private final ZonedDateTime createdAt;

    @Builder
    public MemberSnapshot(MemberSnapshotId id, MemberId memberId, SnapshotId snapshotId, InsightId insightId, ZonedDateTime createdAt) {
        setId(id);
        this.memberId = memberId;
        this.snapshotId = snapshotId;
        this.insightId = insightId;
        this.createdAt = createdAt;
    }
}
