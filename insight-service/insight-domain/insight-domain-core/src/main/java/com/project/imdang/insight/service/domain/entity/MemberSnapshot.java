package com.project.imdang.insight.service.domain.entity;


import com.project.imdang.domain.entity.BaseEntity;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.valueobject.MemberSnapshotId;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class MemberSnapshot extends BaseEntity<MemberSnapshotId> {

    private final MemberId memberId;
    private final SnapshotId snapshotId;
    private final ZonedDateTime createdAt;

    public MemberSnapshot(MemberId memberId, SnapshotId snapshotId, ZonedDateTime createdAt) {
        this.memberId = memberId;
        this.snapshotId = snapshotId;
        this.createdAt = createdAt;
    }
}
