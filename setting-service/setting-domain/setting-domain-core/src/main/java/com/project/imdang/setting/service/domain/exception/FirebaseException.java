package com.project.imdang.setting.service.domain.exception;

public class FirebaseException extends NotificationDomainException{
    public FirebaseException(String message) {
        super(message);
    }

    public FirebaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
