package com.project.imdang.member.application.dto.login;

import com.project.imdang.common.domain.valueobject.OAuthProvider;

public record LoginRequest(
        OAuthProvider provider,
        String identifier
) {
}
