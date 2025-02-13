package com.project.imdang.member.service.domain.entity;


import com.project.imdang.domain.entity.AggregateRoot;
import com.project.imdang.domain.valueobject.CouponId;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.exception.MemberCouponDomainException;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class MemberCoupon extends AggregateRoot<MemberCouponId> {

    private final CouponId couponId;
    private final MemberId memberId;
    private final ZonedDateTime expiredAt;

    private String remark;  // reason
    private ZonedDateTime createdAt;

    private boolean used;
    private ZonedDateTime usedAt;

    @Builder
    public MemberCoupon(MemberCouponId id, CouponId couponId, MemberId memberId, ZonedDateTime expiredAt, String remark, ZonedDateTime createdAt, boolean used, ZonedDateTime usedAt) {
        setId(id);
        this.couponId = couponId;
        this.memberId = memberId;
        this.expiredAt = expiredAt;
        this.remark = remark;
        this.createdAt = createdAt;
        this.used = used;
        this.usedAt = usedAt;
    }

    public void initialize() {
        this.createdAt = ZonedDateTime.now();
    }

    public void use() {
        if (used) {
            throw new MemberCouponDomainException("Already used memberCoupon!");
        }
        this.used = true;
        this.usedAt = ZonedDateTime.now();
    }

    public void cancel() {
        if (!used) {
            throw new MemberCouponDomainException("Not used memberCoupon!");
        }
        this.used = false;
        this.usedAt = null;
    }
}
