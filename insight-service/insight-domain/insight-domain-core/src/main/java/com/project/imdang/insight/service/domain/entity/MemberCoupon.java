package com.project.imdang.insight.service.domain.entity;


import com.project.imdang.domain.valueobject.CouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.valueobject.MemberCouponId;

import java.time.ZonedDateTime;

public class MemberCoupon {

    private MemberCouponId id;
    private final CouponId couponId;
    private final MemberId memberId;

    // TODO - CHECK : only 사용/미사용 + 사용 취소
    private ZonedDateTime expirationDate;

    private String remark;  // reason
    private ZonedDateTime createdAt;

//    private CouponStatus status;
    private boolean used = false;
    private ZonedDateTime usedAt;

    // TODO : expirationDate 추가
    public MemberCoupon(CouponId couponId, MemberId memberId) {
        this.couponId = couponId;
        this.memberId = memberId;
    }

    public void use() {
//        if (status != null && status == CouponStatus.USED) {
//
//        }
//        this.status = CouponStatus.USED;
    }
}
