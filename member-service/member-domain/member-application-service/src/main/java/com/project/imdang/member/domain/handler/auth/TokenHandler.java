package com.project.imdang.member.domain.handler.auth;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.dto.login.TokenResult;
import com.project.imdang.member.domain.ports.output.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenHandler {

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 5;        // 5시간
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일

    private final TokenProvider tokenProvider;

    public TokenResult generateToken(MemberId memberId) {

        long current = System.currentTimeMillis();
        final Date accessTokenExpiredAt = new Date(current + ACCESS_TOKEN_EXPIRE_TIME);
        final Date refreshTokenExpiredAt = new Date(current + REFRESH_TOKEN_EXPIRE_TIME);
        final String subject = memberId.getValue().toString();

        final String accessToken = tokenProvider.generate(subject, accessTokenExpiredAt);
        log.info("AccessToken of member[id : {}] is generated : {}.", memberId.getValue(), accessToken);
        final String refreshToken = tokenProvider.generate("RefreshToken", refreshTokenExpiredAt);
        log.info("RefreshToken of member[id : {}] is generated : {}.", memberId.getValue(), refreshToken);
        return TokenResult.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
