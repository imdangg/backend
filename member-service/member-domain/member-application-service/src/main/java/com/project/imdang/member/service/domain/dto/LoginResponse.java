package com.project.imdang.member.service.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "로그인 응답")
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
    @Schema(description = "온보딩 여부")
    private boolean isJoined;
    private UUID memberId;

    public static LoginResponse from(TokenResponse response, boolean isJoined, UUID memberId) {
        return new LoginResponse(response.getAccessToken(), response.getRefreshToken(), response.getExpiresIn(), isJoined, memberId);
    }
}
