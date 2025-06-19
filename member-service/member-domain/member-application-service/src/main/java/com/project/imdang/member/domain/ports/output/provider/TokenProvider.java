package com.project.imdang.member.domain.ports.output.provider;

import java.util.Date;

public interface TokenProvider {

    /**
     * AccessToken 생성 함수
     */
    String generateAccessToken(String subject, Date expiredAt);

    /**
     * RefreshToken 생성 함수
     */
    String generateRefreshToken(Date expiredAt);

    /**
     * 토큰 유효성 검증
     */
    boolean verifyToken(String token);

    String extractSubject(String token);
}
