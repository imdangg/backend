package com.project.imdang.application.handler;

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
}
