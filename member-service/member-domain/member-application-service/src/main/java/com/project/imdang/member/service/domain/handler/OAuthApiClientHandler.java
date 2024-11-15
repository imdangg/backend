package com.project.imdang.member.service.domain.handler;

import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginParamsCommand;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.member.service.domain.valueobject.OAuthType;

public interface OAuthApiClientHandler {
    OAuthType oAuthType();
    String getAccessToken(OAuthLoginParamsCommand loginCommand);
    OAuthLoginResponse getOAuthInfo(String accessToken);
}
