package com.project.imdang.member.service.domain.handler;

import com.project.imdang.member.service.domain.dto.TokenResponse;
import com.project.imdang.member.service.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class TokenRequestHandler {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60;            // 60분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일

    public TokenResponse generate(Member member) {
        long now = (new Date()).getTime();
        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        String subject = member.getId().getValue().toString();
        String accessToken = jwtTokenProvider.generateAccessToken(subject, accessTokenExpiredAt);
        log.info("Member[id : {}] AccessToken is Generated : {}", member.getId().getValue(), accessToken);

        String refreshToken = jwtTokenProvider.generateRefreshToken(refreshTokenExpiredAt);
        log.info("Member[id : {}] RefreshToken is Generated : {}", member.getId().getValue(), refreshToken);

        return new TokenResponse(accessToken, refreshToken,ACCESS_TOKEN_EXPIRE_TIME / 1000L);
    }

    public UUID extractMemberId(String accessToken) {
        //TODO : 토큰 유효성 검사
       return UUID.fromString(jwtTokenProvider.extractSubject(accessToken));
    }

    public void storeRefreshToken(String oAuthId, String refreshToken) {

    }
}
