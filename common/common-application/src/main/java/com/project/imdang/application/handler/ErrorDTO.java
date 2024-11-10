package com.project.imdang.application.handler;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorDTO {
    private final String code;
    private final String message;

    @Builder
    public ErrorDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
