package com.project.imdang.member.application.dto.member;

import com.project.imdang.common.domain.valueobject.Gender;

public record JoinRequest(
        String nickname,
        String birthDate,
        Gender gender,
        String deviceToken
) {
}
