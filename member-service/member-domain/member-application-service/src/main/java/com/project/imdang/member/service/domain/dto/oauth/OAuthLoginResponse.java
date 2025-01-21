package com.project.imdang.member.service.domain.dto.oauth;

import com.project.imdang.member.service.domain.valueobject.OAuthType;

public interface OAuthLoginResponse {
    String getNickname();
    String getEmail();
    String getId();
    String getRefreshToken();
    OAuthType getOAuthType();
}
