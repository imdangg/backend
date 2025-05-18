package com.project.imdang.member;

import com.project.imdang.common.domain.valueobject.Gender;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.entity.Member;

import java.util.UUID;

public final class TestData {

    static UUID memberId = UUID.fromString("8a1e1c3d-74ef-42c8-9239-a33455b02eda");
    static String fcmToken = "fcm-token";
    static String accessToken = "access-token";

    static Member member = Member.builder()
            .id(new MemberId(memberId))
            .nickname("imdang")
            .birthDate("2024-12-31")
            .gender(Gender.MALE)
            .deviceToken(fcmToken)
            .insightCount(1)
            .build();
}
