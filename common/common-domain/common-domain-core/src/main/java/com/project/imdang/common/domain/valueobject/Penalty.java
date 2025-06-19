package com.project.imdang.common.domain.valueobject;

import lombok.Getter;

@Getter
public enum Penalty {
// TODO - CHECK : vs DB / interface
    // 이용 정지 - 7일
    TEMPORARY_BANNED(MemberStatus.TEMPORARY_BANNED, 7),
    // 계정 정지
    PERMANENT_BANNED(MemberStatus.PERMANENT_BANNED,-1);

    private final MemberStatus memberStatus;
    private final int days;

    Penalty(MemberStatus memberStatus, int days) {
        this.memberStatus = memberStatus;
        this.days = days;
    }
}
