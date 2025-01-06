package com.project.imdang.application.handler;

import com.project.imdang.application.security.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorDTO {
    private final String code;
    private final String message;

    @Builder
    private ErrorDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorDTO of(HttpStatus status, String message) {
        return ErrorDTO.builder()
                .code(status.getReasonPhrase())
                .message(message)
                .build();
    }

    public static ErrorDTO of(ErrorCode errorCode) {
        return ErrorDTO.builder()
                .code(errorCode.getErrorCode())
                .message(errorCode.getMessage())
                .build();
    }
}
