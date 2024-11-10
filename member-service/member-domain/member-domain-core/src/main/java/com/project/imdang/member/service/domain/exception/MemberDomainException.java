package com.project.imdang.member.service.domain.exception;

import com.project.imdang.domain.exception.DomainException;

public class MemberDomainException extends DomainException {

    public MemberDomainException(String message) {
        super(message);
    }

    public MemberDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
