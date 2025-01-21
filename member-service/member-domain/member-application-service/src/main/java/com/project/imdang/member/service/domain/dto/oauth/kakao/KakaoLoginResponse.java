package com.project.imdang.member.service.domain.dto.oauth.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoLoginResponse implements OAuthLoginResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class KakaoAccount {
        private KakaoProfile profile;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class KakaoProfile {
        private String nickName;
    }

    @Override
    public String getId() {
        return id;
    }
    @Override
    public String getNickname() {
        return kakaoAccount.profile.nickName;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public OAuthType getOAuthType() {
        return OAuthType.KAKAO;
    }
    @Override
    public String getRefreshToken() {
        return null;
    }
}
