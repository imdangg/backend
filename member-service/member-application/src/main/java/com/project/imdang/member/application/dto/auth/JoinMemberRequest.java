package com.project.imdang.member.application.dto.auth;

import com.project.imdang.common.domain.valueobject.Gender;

public record JoinMemberRequest(
        String nickname,
        String birthDate,
        Gender gender,
        String deviceToken
) {
}
