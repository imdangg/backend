package com.project.imdang.member.service.domain.dto.oauth;

import com.project.imdang.member.service.domain.valueobject.OAuthType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OAuthWithdrawCommand_ {
    private String oAuthType;
    private String token;

    public OAuthType oAuthType() {
        return OAuthType.valueOf(oAuthType.toUpperCase());
    }

    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("token", token);
        return body;
    }
}
