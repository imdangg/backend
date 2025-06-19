package com.project.imdang.common.application.response;

import com.project.imdang.common.application.response.code.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {
    private final String code;
    private final String message;

    @Builder
    private ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse of(HttpStatus status, String message) {
        return ErrorResponse.builder()
                .code(status.getReasonPhrase())
                .message(message)
                .build();
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .code(errorCode.getErrorCode())
                .message(errorCode.getMessage())
                .build();
    }

    public static ErrorResponse of(ErrorCode errorCode, String message) {
        return ErrorResponse.builder()
                .code(errorCode.getErrorCode())
                .message(message)
                .build();
    }
}
