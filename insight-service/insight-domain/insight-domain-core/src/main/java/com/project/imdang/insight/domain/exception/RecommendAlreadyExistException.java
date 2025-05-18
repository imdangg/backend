package com.project.imdang.insight.domain.exception;

import com.project.imdang.common.domain.exception.DomainAlreadyExistException;

public class RecommendAlreadyExistException extends DomainAlreadyExistException {

    public RecommendAlreadyExistException() {
        super("Recommend Already Exist");
    }

    public RecommendAlreadyExistException(String message) {
        super(message);
    }

    public RecommendAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
