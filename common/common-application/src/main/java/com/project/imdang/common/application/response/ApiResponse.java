package com.project.imdang.common.application.response;

import com.project.imdang.common.application.response.code.ErrorCode;
import lombok.Getter;

@Getter // fix/EZTX-23 HttpMediaTypeNotAcceptableException 해결
public class ApiResponse<T> {
    // NOTE: HttpStatus는 Spring의 ResponseEntity로 설정
    private final T data;
    private final ErrorResponse error;

    private ApiResponse(T data, ErrorResponse error) {
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data, null);
    }

    public static <T> ApiResponse<T> error(ErrorCode errorCode) {
        return new ApiResponse<>(null, ErrorResponse.of(errorCode));
    }

    // violation 에러
    public static <T> ApiResponse<T> error(ErrorCode errorCode, String errorMessage) {
        return new ApiResponse<>(null, ErrorResponse.of(errorCode, errorMessage));
    }
}
