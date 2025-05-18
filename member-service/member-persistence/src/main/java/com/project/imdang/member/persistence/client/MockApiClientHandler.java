package com.project.imdang.member.persistence.client;


import com.project.imdang.member.domain.dto.auth.OAuthLoginCommand;
import com.project.imdang.member.domain.dto.auth.OAuthLoginResponse;
import com.project.imdang.member.domain.dto.member.OAuthWithdrawCommand;
import com.project.imdang.member.domain.ports.output.client.OAuthApiClientHandler;
import com.project.imdang.common.domain.valueobject.OAuthType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;


@Component
@RequiredArgsConstructor
public class MockApiClientHandler implements OAuthApiClientHandler {

    @Override
    public OAuthType oAuthType() {
        return OAuthType.MOCK;
    }

    @Override
    public OAuthLoginResponse getOAuthInfo(OAuthLoginCommand loginCommand) {
        return new OAuthLoginResponse() {
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
                MultiValueMap<String, String> body = loginCommand.makeBody();
                return body.getFirst("id");
            }

            @Override
            public String getRefreshToken() {
                return null;
            }

            @Override
            public OAuthType getOAuthType() {
                return OAuthType.MOCK;
            }
        };
    }

    @Override
    public void withdraw(OAuthWithdrawCommand withdrawCommand) {
    }
}
