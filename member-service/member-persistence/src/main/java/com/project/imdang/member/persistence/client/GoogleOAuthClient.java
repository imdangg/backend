package com.project.imdang.member.persistence.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.imdang.common.domain.valueobject.OAuthProvider;
import com.project.imdang.member.domain.ports.output.client.OAuthInfo;
import com.project.imdang.member.domain.ports.output.client.OAuthClient;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@RequiredArgsConstructor
@Component
public class GoogleOAuthClient implements OAuthClient {

    private final RestTemplate restTemplate;

    @Value("${oauth.google.url.api}")
    private String apiUrl;

    @Value("${oauth.google.url.withdraw-api}")
    private String withdrawUrl;

    @Override
    public OAuthProvider getProvider() {
        return OAuthProvider.GOOGLE;
    }

    @Override
    public GoogleOAuthInfo getOAuthInfo(String identifier) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(identifier);

        HttpEntity<?> request = new HttpEntity<>(headers);
        return restTemplate.exchange(apiUrl, HttpMethod.GET, request, GoogleOAuthInfo.class).getBody();
    }

    @Override
    public void withdraw(String identifier) {
        final String url = UriComponentsBuilder.fromHttpUrl(withdrawUrl)
                .queryParam("token", identifier)
                .toUriString();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("token", identifier);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<?> request = new HttpEntity<>(httpHeaders);
        restTemplate.postForObject(url, request, Void.class);
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class GoogleOAuthInfo implements OAuthInfo {
        @JsonProperty("id") private String id;
        @JsonProperty("email") private String email;
        @JsonProperty("verified_email") private boolean isVerified;
        @JsonProperty("name") private String name;

        @Override
        public String getId() {
            return id;
        }
        @Override
        public String getNickname() {
            return name;
        }
        @Override
        public String getEmail() {
            return email;
        }
    }

//    @Getter
//    @NoArgsConstructor(access = AccessLevel.PRIVATE)
//    static class GoogleTokenResponse {
//        @JsonProperty("token_type") private String tokenType;
//        @JsonProperty("access_token") private String accessToken;
//        @JsonProperty("refresh_token") private String refreshToken;
//        @JsonProperty("expires_in") private String expiresIn;
//        @JsonProperty("scope") private String scope;
//        @JsonProperty("id_token") private String idToken;
//    }
}
