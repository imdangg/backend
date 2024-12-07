package com.project.imdang.member.service.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
    private boolean isJoined;

    public static LoginResponse from(TokenResponse response, boolean isJoined) {
        return new LoginResponse(response.getAccessToken(), response.getRefreshToken(), response.getExpiresIn(), isJoined);
    }
}
