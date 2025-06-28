package com.project.imdang.member.domain.handler.auth;

import com.project.imdang.member.domain.ports.output.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenHandler {

    private static final String ACCESS_TOKEN = "access_token";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 5;        // 5시간
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일

    private final TokenProvider tokenProvider;

    public String generateAccessToken(UUID memberId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("memberId", memberId);
        final String accessToken = tokenProvider.generate(ACCESS_TOKEN, claims, new Date(new Date().getTime() + ACCESS_TOKEN_EXPIRE_TIME));
        log.info("AccessToken of member[id : {}] is generated : {}.", memberId, accessToken);
        return accessToken;
    }

    public Map<String, Object> validateAndExtractClaimsFromAccessToken(String accessToken) {
        tokenProvider.validate(accessToken);
        return tokenProvider.extractClaims(accessToken);
    }

    public String generateRefreshToken(UUID memberId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("memberId", memberId);
        final String refreshToken = tokenProvider.generate(REFRESH_TOKEN, claims, new Date(new Date().getTime() + REFRESH_TOKEN_EXPIRE_TIME));
        log.info("RefreshToken of member[id : {}] is generated : {}.", memberId, refreshToken);
        return refreshToken;
    }
}
