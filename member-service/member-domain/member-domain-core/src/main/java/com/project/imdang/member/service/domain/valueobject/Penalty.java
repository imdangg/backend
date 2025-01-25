package com.project.imdang.member.service.domain.valueobject;

import lombok.Getter;

import static com.project.imdang.member.service.domain.valueobject.MemberStatus.EXCHANGE_RESTRICTED;

@Getter
public enum Penalty {
// TODO - CHECK : vs DB / interface

    // 교환 불가 - 3일
    EXCHANGE_RESTRICTED_3DAYS(EXCHANGE_RESTRICTED, 3),
    // 교환 불가 - 5일
    EXCHANGE_RESTRICTED_5DAYS(EXCHANGE_RESTRICTED, 5),
    // 이용 정지 - 7일
    TEMPORARY_BANNED(MemberStatus.TEMPORARY_BANNED, 7),
    // 계정 정지
    PERMANENT_BANNED(MemberStatus.PERMANENT_BANNED,-1);

    private MemberStatus memberStatus;
    private int days;

    Penalty(MemberStatus memberStatus, int days) {
        this.memberStatus = memberStatus;
        this.days = days;
    }
}
