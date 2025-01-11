package com.project.imdang.insight.service.domain.entity;


import com.project.imdang.domain.entity.AggregateRoot;
import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
public class ExchangeRequest extends AggregateRoot<ExchangeRequestId> {
    private MemberId requestMemberId;

    ////////////////////////////////////////////////////////////
    private final InsightId requestMemberInsightId;
    private SnapshotId requestMemberSnapshotId;
    // OR
    private MemberCouponId memberCouponId;
    ////////////////////////////////////////////////////////////

    private final InsightId requestedInsightId;
    private SnapshotId requestedSnapshotId;
    private MemberId requestedMemberId;

    private ZonedDateTime requestedAt;
    private ZonedDateTime respondedAt;
    private ExchangeRequestStatus status;

    @Builder
    public ExchangeRequest(ExchangeRequestId id,
                           MemberId requestMemberId,
                           InsightId requestMemberInsightId,
                           SnapshotId requestMemberSnapshotId,
                           MemberCouponId memberCouponId,
                           InsightId requestedInsightId,
                           SnapshotId requestedSnapshotId,
                           MemberId requestedMemberId,
                           ZonedDateTime requestedAt,
                           ZonedDateTime respondedAt,
                           ExchangeRequestStatus status) {
        setId(id);
        this.requestMemberId = requestMemberId;
        this.requestMemberInsightId = requestMemberInsightId;
        this.requestMemberSnapshotId = requestMemberSnapshotId;
        this.memberCouponId = memberCouponId;
        this.requestedInsightId = requestedInsightId;
        this.requestedSnapshotId = requestedSnapshotId;
        this.requestedMemberId = requestedMemberId;
        this.requestedAt = requestedAt;
        this.respondedAt = respondedAt;
        this.status = status;
    }

    public void initialize(Snapshot requestedSnapshot, Snapshot requestMemberSnapshot, MemberCouponId memberCouponId) {
        
        // TODO - validation 체크

        // TODO - 동일 글 중복 교환 요청 불가 (1개 글에 요청 1번으로 제한)
        // requestMemberId - requestedInsightId UNIQUE
        ExchangeRequestId id = new ExchangeRequestId(UUID.randomUUID());
        setId(id);

        this.requestedMemberId = requestedSnapshot.getMemberId();
        this.requestedSnapshotId = requestedSnapshot.getId();
        this.requestedAt = ZonedDateTime.now();

        if (requestMemberSnapshot != null) {
            this.requestMemberId = requestMemberSnapshot.getMemberId();
            this.requestMemberSnapshotId = requestMemberSnapshot.getId();

            this.status = ExchangeRequestStatus.PENDING;
        } else {
            // 쿠폰 사용 요청
            this.memberCouponId = memberCouponId;
        }
    }

    public void completeCheckCoupon() {
        this.status = ExchangeRequestStatus.PENDING;
    }

    public void accept() {
        this.status = ExchangeRequestStatus.ACCEPTED;
        this.respondedAt = ZonedDateTime.now();
    }

    public void reject() {
        this.status = ExchangeRequestStatus.REJECTED;
        this.respondedAt = ZonedDateTime.now();
    }
}
