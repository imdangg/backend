package com.project.imdang.member.service.domain.handler.auth;

import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.OAuthWithdrawCommand;
import com.project.imdang.member.service.domain.valueobject.OAuthType;

public interface OAuthApiClientHandler {
    OAuthType oAuthType();
    OAuthLoginResponse getOAuthInfo(OAuthLoginCommand loginCommand);
    void withdraw(OAuthWithdrawCommand withdrawCommand);
}
