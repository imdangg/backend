package com.project.imdang.member.service.domain.dto.oauth.apple;

import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginParamsCommand;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class AppleLoginParamsCommand implements OAuthLoginParamsCommand {
    private String authorizationCode;
    @Override
    public OAuthType oAuthType() {
        return OAuthType.APPLE;
    }

    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", authorizationCode);
        return body;
    }
}