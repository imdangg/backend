package com.project.imdang.member.domain.ports.output.client;

import com.project.imdang.common.domain.valueobject.OAuthProvider;

public interface OAuthClient {
    OAuthProvider getProvider();
    OAuthInfo getOAuthInfo(String identifier);
    void withdraw(String identifier);
}
