package com.project.imdang.member.service.domain.exception;

import com.project.imdang.domain.valueobject.CouponId;
import com.project.imdang.domain.valueobject.MemberId;

public class CouponNotFoundException extends CouponDomainException {

    public CouponNotFoundException(CouponId couponId) {
        this(String.format("Could not find coupon[id: %s]", couponId.getValue()));
    }

    private CouponNotFoundException(String message) {
        super(message);
    }

    private CouponNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
