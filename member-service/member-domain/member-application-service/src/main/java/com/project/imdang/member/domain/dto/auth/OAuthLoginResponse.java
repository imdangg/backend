package com.project.imdang.member.domain.dto.auth;

import com.project.imdang.common.domain.valueobject.OAuthType;

public interface OAuthLoginResponse {
    String getNickname();
    String getEmail();
    String getId();
    String getRefreshToken();
    OAuthType getOAuthType();
}
