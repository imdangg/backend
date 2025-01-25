package com.project.imdang.member.service.domain.valueobject;

public enum MemberStatus {
    // 교환 불가
    EXCHANGE_RESTRICTED,
    // 이용 정지
    TEMPORARY_BANNED,
    // 계정 정지
    PERMANENT_BANNED,
    // 정상
    ACTIVE
}
