package com.project.imdang.member.service.domain.dto.oauth.google;

import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginParamsCommand;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class GoogleLoginParamsCommand implements OAuthLoginParamsCommand {
    private String authorizationCode;

    @Override
    public OAuthType oAuthType() {
        return OAuthType.GOOGLE;
    }

    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", authorizationCode);
        return body;
    }
}
