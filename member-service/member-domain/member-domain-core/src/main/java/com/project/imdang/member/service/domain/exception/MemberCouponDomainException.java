package com.project.imdang.member.service.domain.exception;

import com.project.imdang.domain.exception.DomainException;

public class MemberCouponDomainException extends DomainException {

    public MemberCouponDomainException(String message) {
        super(message);
    }

    public MemberCouponDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
