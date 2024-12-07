package com.project.imdang.member.service.domain.entity;


import com.project.imdang.domain.entity.BaseEntity;
import com.project.imdang.domain.valueobject.CouponId;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.exception.MemberCouponDomainException;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class MemberCoupon extends BaseEntity<MemberCouponId> {

    private final CouponId couponId;
    private final MemberId memberId;

    // TODO - CHECK : only 사용/미사용 + 사용 취소
    private ZonedDateTime expiredAt;

    private String remark;  // reason
    private ZonedDateTime createdAt;

    private Boolean used;
    private ZonedDateTime usedAt;

    @Builder
    public MemberCoupon(MemberCouponId id, CouponId couponId, MemberId memberId, ZonedDateTime expiredAt, String remark, ZonedDateTime createdAt, Boolean used, ZonedDateTime usedAt) {
        setId(id);
        this.couponId = couponId;
        this.memberId = memberId;
        this.expiredAt = expiredAt;
        this.remark = remark;
        this.createdAt = createdAt;
        this.used = used;
        this.usedAt = usedAt;
    }

    public void use() {
        if (Boolean.TRUE.equals(used)) {
            throw new MemberCouponDomainException("Already used memberCoupon!");
        }
        this.used = Boolean.TRUE;
        this.usedAt = ZonedDateTime.now();
    }
}
