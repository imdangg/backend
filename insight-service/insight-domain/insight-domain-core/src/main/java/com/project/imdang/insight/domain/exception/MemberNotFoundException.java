package com.project.imdang.insight.domain.exception;

import com.project.imdang.common.domain.exception.DomainNotFoundException;
import com.project.imdang.common.domain.valueobject.MemberId;

public class MemberNotFoundException extends DomainNotFoundException {

    public MemberNotFoundException(MemberId memberId) {
        this(String.format("Could not find member[id: %s]!", memberId.getValue()));
    }

    public MemberNotFoundException(String message) {
        super(message);
    }

    public MemberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
