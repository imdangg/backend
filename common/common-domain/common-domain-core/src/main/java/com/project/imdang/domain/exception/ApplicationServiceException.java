package com.project.imdang.domain.exception;

public class ApplicationServiceException extends DomainException {

    public ApplicationServiceException(String message) {
        super(message);
    }

    public ApplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
