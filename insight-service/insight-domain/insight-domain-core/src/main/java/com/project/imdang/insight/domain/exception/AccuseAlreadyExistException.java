package com.project.imdang.insight.domain.exception;

import com.project.imdang.common.domain.exception.DomainAlreadyExistException;

public class AccuseAlreadyExistException extends DomainAlreadyExistException {

    public AccuseAlreadyExistException() {
        super("Accuse Already Exist");
    }

    public AccuseAlreadyExistException(String message) {
        super(message);
    }

    public AccuseAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
