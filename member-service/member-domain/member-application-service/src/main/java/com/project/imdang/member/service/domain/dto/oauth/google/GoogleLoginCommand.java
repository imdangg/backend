package com.project.imdang.member.service.domain.dto.oauth.google;

import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoogleLoginCommand implements OAuthLoginCommand {
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
