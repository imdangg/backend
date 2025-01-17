package com.project.imdang.member.service.domain.dto.oauth.apple;


import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppleLoginResponse implements OAuthLoginResponse {
    private String id;
    private String email;

    @Override
    public String getNickname() {
        return null;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public OAuthType getOAuthType() {
        return OAuthType.APPLE;
    }
}
