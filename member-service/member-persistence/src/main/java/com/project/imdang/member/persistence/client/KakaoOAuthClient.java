package com.project.imdang.member.persistence.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.imdang.common.domain.valueobject.OAuthProvider;
import com.project.imdang.member.domain.ports.output.client.OAuthClient;
import com.project.imdang.member.domain.ports.output.client.OAuthInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@RequiredArgsConstructor
@Component
public class KakaoOAuthClient implements OAuthClient {

    private final RestTemplate restTemplate;

    @Value("${oauth.kakao.url.api}")
    private String apiUrl;
    @Value("${oauth.kakao.url.withdraw-api}")
    private String withdrawUrl;

    @Override
    public OAuthProvider getOAuthProvider() {
        return OAuthProvider.KAKAO;
    }

    @Override
    public KakaoOAuthInfo getOAuthInfo(String identifier) {
        final HttpHeaders headers = getHeaders(identifier);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("property_keys", "[\"kakao_account.profile]\"]");

        HttpEntity<?> request = new HttpEntity<>(body, headers);
        return restTemplate.postForObject(apiUrl, request, KakaoOAuthInfo.class);
    }

    @Override
    public void withdraw(String identifier) {

        final HttpHeaders headers = getHeaders(identifier);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("token", identifier);

        HttpEntity<?> request = new HttpEntity<>(body, headers);
        restTemplate.postForObject(withdrawUrl, request, KakaoWithdrawResponse.class);
    }

    private HttpHeaders getHeaders(String identifier) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(identifier);
        return headers;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class KakaoOAuthInfo implements OAuthInfo {

        @JsonProperty("id")
        private String id;

        @JsonProperty("kakao_account")
        private KakaoAccount kakaoAccount;

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

        @JsonIgnoreProperties(ignoreUnknown = true)
        static class KakaoAccount {
            private KakaoProfile profile;

            @JsonIgnoreProperties(ignoreUnknown = true)
            static class KakaoProfile {
                private String nickName;
            }
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class KakaoWithdrawResponse{
        @JsonProperty("id") private String id;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class KakaoTokenResponse {
        @JsonProperty("token_type") private String tokenType;
        @JsonProperty("access_token") private String accessToken;
        @JsonProperty("refresh_token") private String refreshToken;
        @JsonProperty("expires_in") private String expiresIn;
        @JsonProperty("refresh_token_expires_in") private String refreshTokenExpiresIn;
    }
}
