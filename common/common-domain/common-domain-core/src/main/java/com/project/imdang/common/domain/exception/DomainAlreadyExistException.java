package com.project.imdang.common.domain.exception;

public class DomainAlreadyExistException extends RuntimeException {

    public DomainAlreadyExistException(String message) {
        super(message);
    }

    public DomainAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
