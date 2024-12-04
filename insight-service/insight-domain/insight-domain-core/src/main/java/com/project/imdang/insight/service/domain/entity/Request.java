package com.project.imdang.insight.service.domain.entity;


import com.project.imdang.domain.entity.AggregateRoot;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.valueobject.RequestId;
import com.project.imdang.insight.service.domain.valueobject.RequestStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class Request extends AggregateRoot<RequestId> {
    private final MemberId requestMemberId;
    private final MemberCouponId memberCouponId;
    private final InsightId requestedInsightId;

    // TODO - CHECK : vs createdAt
    private final ZonedDateTime requestedAt;
    private ZonedDateTime respondedAt;
    private RequestStatus status;

    @Builder
    public Request(RequestId id, MemberId requestMemberId, MemberCouponId memberCouponId, InsightId requestedInsightId, ZonedDateTime requestedAt, ZonedDateTime respondedAt, RequestStatus status) {
        setId(id);
        this.requestMemberId = requestMemberId;
        this.memberCouponId = memberCouponId;
        this.requestedInsightId = requestedInsightId;
        this.requestedAt = requestedAt;
        this.respondedAt = respondedAt;
        this.status = status;
    }

    public static Request createNewRequest(MemberId requestMemberId, MemberCouponId memberCouponId, InsightId requestedInsightId) {
        return Request.builder()
                .requestMemberId(requestMemberId)
                .memberCouponId(memberCouponId)
                .requestedInsightId(requestedInsightId)
                .status(RequestStatus.PENDING)
                .build();
    }

    public void accept() {
        this.status = RequestStatus.ACCEPTED;
        this.respondedAt = ZonedDateTime.now();
    }

    public void reject() {
        this.status = RequestStatus.REJECTED;
        this.respondedAt = ZonedDateTime.now();
    }
}
