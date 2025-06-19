package com.project.imdang.member.domain.exception;

import com.project.imdang.common.domain.exception.DomainException;

public class MemberDomainException extends DomainException {

    public MemberDomainException(String message) {
        super(message);
    }

    public MemberDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
