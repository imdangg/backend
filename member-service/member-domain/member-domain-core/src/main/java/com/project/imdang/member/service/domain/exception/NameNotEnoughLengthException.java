package com.project.imdang.member.service.domain.exception;

public class NameNotEnoughLengthException extends MemberDomainException {

    public NameNotEnoughLengthException(String message) {
        super(message);
    }

    public NameNotEnoughLengthException(String message, Throwable cause) {
        super(message, cause);
    }
}
