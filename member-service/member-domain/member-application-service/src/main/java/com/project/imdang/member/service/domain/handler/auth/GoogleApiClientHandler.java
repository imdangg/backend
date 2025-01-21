package com.project.imdang.member.service.domain.handler.auth;

import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.OAuthWithdrawCommand;
import com.project.imdang.member.service.domain.dto.oauth.google.GoogleLoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.kakao.KakaoWithdrawResponse;
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
import org.springframework.web.util.UriComponentsBuilder;


@Component
@RequiredArgsConstructor
public class GoogleApiClientHandler implements OAuthApiClientHandler {
    private final RestTemplate restTemplate;

    @Value("${oauth.google.url.api}")
    private String apiUrl;

    @Value("${oauth.google.url.api}")
    private String withdrawUrl;

    @Override
    public OAuthType oAuthType() {
        return OAuthType.GOOGLE;
    }

    @Override
    public OAuthLoginResponse getOAuthInfo(OAuthLoginCommand loginCommand) {
        MultiValueMap<String, String> body = loginCommand.makeBody();
        String accessToken = body.getFirst("code");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

        HttpEntity<?> request = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(apiUrl, HttpMethod.GET, request, GoogleLoginResponse.class).getBody();
    }

    @Override
    public void withdraw(OAuthWithdrawCommand withdrawCommand) {
        MultiValueMap<String, String> body = withdrawCommand.makeBody();
        String accessToken = body.getFirst("token");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(withdrawUrl)
                .queryParam("token", accessToken);
        String url= builder.toUriString();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<?> request = new HttpEntity<>(httpHeaders);
        restTemplate.postForObject(url, request, Void.class);
    }
}
