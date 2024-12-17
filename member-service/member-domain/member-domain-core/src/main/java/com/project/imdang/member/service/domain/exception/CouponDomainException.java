package com.project.imdang.member.service.domain.exception;

import com.project.imdang.domain.exception.DomainException;

public class CouponDomainException extends DomainException {

    public CouponDomainException(String message) {
        super(message);
    }

    public CouponDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
