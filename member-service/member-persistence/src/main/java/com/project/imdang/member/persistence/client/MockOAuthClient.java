package com.project.imdang.member.persistence.client;


import com.project.imdang.common.domain.valueobject.OAuthProvider;
import com.project.imdang.member.domain.ports.output.client.OAuthInfo;
import com.project.imdang.member.domain.ports.output.client.OAuthClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MockOAuthClient implements OAuthClient {

    @Override
    public OAuthProvider getProvider() {
        return OAuthProvider.MOCK;
    }

    @Override
    public OAuthInfo getOAuthInfo(String identifier) {
        return new OAuthInfo() {
            @Override
            public String getNickname() {
                return "test";
            }

            @Override
            public String getEmail() {
                return "test@email.com";
            }

            @Override
            public String getId() {
                return identifier;
            }
        };
    }

    @Override
    public void withdraw(String identifier) {
        throw new UnsupportedOperationException();
    }
}
