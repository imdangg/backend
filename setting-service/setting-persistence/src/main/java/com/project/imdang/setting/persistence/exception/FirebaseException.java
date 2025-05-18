package com.project.imdang.setting.persistence.exception;

// TODO - 예외 처리
public class FirebaseException extends RuntimeException {
    public FirebaseException(String message) {
        super(message);
    }

    public FirebaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
