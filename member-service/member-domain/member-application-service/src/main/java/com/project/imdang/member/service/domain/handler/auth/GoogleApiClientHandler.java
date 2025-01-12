package com.project.imdang.member.service.domain.handler.auth;

import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.google.GoogleLoginResponse;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
public class GoogleApiClientHandler implements OAuthApiClientHandler {
    private final RestTemplate restTemplate;

    @Value("${oauth.google.url.api}")
    private String apiUrl;

    @Override
    public OAuthType oAuthType() {
        return OAuthType.GOOGLE;
    }

    @Override
    public String getAccessToken(OAuthLoginCommand loginCommand) {
        MultiValueMap<String, String> body = loginCommand.makeBody();
        return body.getFirst("code");
    }

    @Override
    public OAuthLoginResponse getOAuthInfo(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

        HttpEntity<?> request = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(apiUrl, HttpMethod.GET, request, GoogleLoginResponse.class).getBody();
    }
}
