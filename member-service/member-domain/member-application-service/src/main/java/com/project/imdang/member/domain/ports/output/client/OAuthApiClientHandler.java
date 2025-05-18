package com.project.imdang.member.domain.ports.output.client;

import com.project.imdang.member.domain.dto.auth.OAuthLoginCommand;
import com.project.imdang.member.domain.dto.auth.OAuthLoginResponse;
import com.project.imdang.member.domain.dto.member.OAuthWithdrawCommand;
import com.project.imdang.common.domain.valueobject.OAuthType;

public interface OAuthApiClientHandler {
    OAuthType oAuthType();
    OAuthLoginResponse getOAuthInfo(OAuthLoginCommand loginCommand);
    void withdraw(OAuthWithdrawCommand withdrawCommand);
}
