package com.project.imdang.member.service.domain.handler;

import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.google.GoogleLoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.google.GoogleTokenResponse;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
public class GoogleApiClientHandler implements OAuthApiClientHandler {
    private final RestTemplate restTemplate;
    private static final String GRANT_TYPE = "authorization_code";

    @Value("${ouath.google.url.auth}")
    private String authUrl;

    @Value("${ouath.google.url.api}")
    private String apiUrl;

    @Value("${ouath.kakao.url.redirect}")
    private String redirectUrl;

    @Value("${ouath.google.client-id}")
    private String clientId;

    @Value("${ouath.google.client-secret}")
    private String clientSecret;

    @Override
    public OAuthType oAuthType() {
        return OAuthType.GOOGLE;
    }

    @Override
    public String getAccessToken(OAuthLoginCommand loginCommand) {
        //TODO : code 인코딩 가능성 존재
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = loginCommand.makeBody();
        body.add("grant_type", GRANT_TYPE);
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("redirect_uri", redirectUrl);

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);
        GoogleTokenResponse response = restTemplate.postForObject(authUrl, request, GoogleTokenResponse.class);
        //TODO null 체크
        return response.getAccessToken();
    }

    @Override
    public OAuthLoginResponse getOAuthInfo(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);
        return restTemplate.postForObject(apiUrl, request, GoogleLoginResponse.class);
    }
}
