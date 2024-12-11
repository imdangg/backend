package com.project.imdang.setting.service.domain.exception;

import com.project.imdang.domain.exception.DomainException;

public class NotificationDomainException extends DomainException {

    public NotificationDomainException(String message) {
        super(message);
    }

    public NotificationDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
