package com.project.imdang.member.domain.dto.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenResult {
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
}
