package com.project.imdang.insight.service.domain.entity;


import com.project.imdang.domain.entity.BaseEntity;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.valueobject.ExchangeSnapshotId;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class ExchangeSnapshot extends BaseEntity<ExchangeSnapshotId> {

    private final MemberId requestMemberId;    // requestedBy
    private final SnapshotId snapshotId;
    private final InsightId insightId;
    private final MemberId requestedMemberId;

    private final ZonedDateTime createdAt;

    @Builder
    public ExchangeSnapshot(ExchangeSnapshotId id,
                            MemberId requestMemberId,
                            SnapshotId snapshotId,
                            InsightId insightId,
                            MemberId requestedMemberId,
                            ZonedDateTime createdAt) {
        setId(id);
        this.requestMemberId = requestMemberId;
        this.snapshotId = snapshotId;
        this.insightId = insightId;
        this.requestedMemberId = requestedMemberId;
        this.createdAt = createdAt;
    }
}
