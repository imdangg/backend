package com.project.imdang.domain.exception;

public enum ErrorCode {
    MEMBER_5ACCUSED,
    MEMBER_15ACCUSED,
    ALREADY_ACCUSED,
    ALREADY_RECOMMENDED,
    EXCHANGE_REQUIRED,

    // 요청 실패
    EXCHANGE_REQUEST_FAILED,
    // 요청 수락 실패
    EXCHANGE_REQUEST_ACCEPT_FAILED,
    // 요청 거절 실패
    EXCHANGE_REQUEST_REJECT_FAILED
}
