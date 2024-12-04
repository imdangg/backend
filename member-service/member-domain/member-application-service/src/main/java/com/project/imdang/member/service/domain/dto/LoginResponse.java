package com.project.imdang.member.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
//    private boolean isJoined;

    public static LoginResponse from(TokenResponse response) {
        return new LoginResponse(response.getAccessToken(), response.getRefreshToken(), response.getExpiresIn());
    }
}
