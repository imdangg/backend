package com.project.imdang.member.service.domain.exception;

import com.project.imdang.domain.valueobject.MemberCouponId;

public class MemberCouponNotFoundException extends MemberCouponDomainException {

    public MemberCouponNotFoundException(MemberCouponId memberCouponId) {
        this(String.format("Could not find memberCoupon[id: %s]!", memberCouponId.getValue()));
    }

    private MemberCouponNotFoundException(String message) {
        super(message);
    }

    private MemberCouponNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
