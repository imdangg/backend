package com.project.imdang.member.service.domain.handler;

import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.kakao.KakaoLoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.kakao.KakaoTokenResponse;
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
    private static final String GRANT_TYPE = "authorization_code";

    @Value("${ouath.kakao.url.auth}")
    private String authUrl;

    @Value("${ouath.kakao.url.api}")
    private String apiUrl;

    @Value("${ouath.kakao.client-id}")
    private String clientId;

    @Override
    public OAuthType oAuthType() {
        return OAuthType.KAKAO;
    }

    @Override
    public String getAccessToken(OAuthLoginCommand loginCommand) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = loginCommand.makeBody();
        body.add("grant_type", GRANT_TYPE);
        body.add("client_id", clientId);

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);
        KakaoTokenResponse response = restTemplate.postForObject(authUrl, request, KakaoTokenResponse.class);
        //TODO null 체크
        return response.getAccessToken();
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
}
