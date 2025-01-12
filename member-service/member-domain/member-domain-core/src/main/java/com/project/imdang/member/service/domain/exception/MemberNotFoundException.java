package com.project.imdang.member.service.domain.exception;

import com.project.imdang.domain.valueobject.MemberId;

public class MemberNotFoundException extends MemberDomainException {

    public MemberNotFoundException(MemberId memberId) {
        this(String.format("Could not find member[id: %s]!", memberId.getValue()));
    }

    private MemberNotFoundException(String message) {
        super(message);
    }

    private MemberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
