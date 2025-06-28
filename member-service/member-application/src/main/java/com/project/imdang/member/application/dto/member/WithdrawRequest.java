package com.project.imdang.member.application.dto.member;

import com.project.imdang.common.domain.valueobject.OAuthProvider;

public record WithdrawRequest(
        OAuthProvider oAuthProvider,
        String identifier
) {
}
