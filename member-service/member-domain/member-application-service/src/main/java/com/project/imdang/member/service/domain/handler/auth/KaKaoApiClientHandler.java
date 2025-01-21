package com.project.imdang.member.service.domain.handler.auth;

import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.OAuthWithdrawCommand;
import com.project.imdang.member.service.domain.dto.oauth.kakao.KakaoLoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.kakao.KakaoWithdrawResponse;
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
public class KaKaoApiClientHandler implements OAuthApiClientHandler{
    private final RestTemplate restTemplate;

    @Value("${oauth.kakao.url.api}")
    private String apiUrl;

    @Value("${oauth.kakao.url.api}")
    private String withdrawUrl;

    @Override
    public OAuthType oAuthType() {
        return OAuthType.KAKAO;
    }

    @Override
    public String getAccessToken(OAuthLoginCommand loginCommand) {
        MultiValueMap<String, String> body = loginCommand.makeBody();
        return body.getFirst("code");
    }

    @Override
    public OAuthLoginResponse getOAuthInfo(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("property_keys", "[\"kakao_account.profile]\"]");

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);
        return restTemplate.postForObject(apiUrl, request, KakaoLoginResponse.class);
    }

    @Override
    public void withdraw(OAuthWithdrawCommand withdrawCommand) {
        MultiValueMap<String, String> body = withdrawCommand.makeBody();
        String accessToken = body.getFirst("token");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);
        KakaoWithdrawResponse kakaoWithdrawResponse = restTemplate.postForObject(withdrawUrl, request, KakaoWithdrawResponse.class);
    }
}
