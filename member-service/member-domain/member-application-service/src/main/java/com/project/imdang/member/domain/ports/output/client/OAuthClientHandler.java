package com.project.imdang.member.domain.ports.output.client;

import com.project.imdang.common.domain.valueobject.OAuthProvider;
import com.project.imdang.member.domain.ports.output.client.OAuthInfo;

public interface OAuthClientHandler {
    OAuthInfo getOAuthInfo(OAuthProvider oAuthProvider, String identifier);
    void withdraw(OAuthProvider oAuthProvider, String identifier);
}
