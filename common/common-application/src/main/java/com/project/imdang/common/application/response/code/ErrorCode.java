package com.project.imdang.common.application.response.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    BAD_REQUEST("4000", HttpStatus.BAD_REQUEST.getReasonPhrase()),
    UNAUTHORIZED("4010", HttpStatus.UNAUTHORIZED.getReasonPhrase()),
    FORBIDDEN("4030", HttpStatus.FORBIDDEN.getReasonPhrase()),
    NOT_FOUND("4040", HttpStatus.NOT_FOUND.getReasonPhrase()),
    ALREADY_EXIST("4090", "Already Exist"),
    INTERNAL_SERVER_ERROR("5000", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

    private final String code;
    private final String message;
}
