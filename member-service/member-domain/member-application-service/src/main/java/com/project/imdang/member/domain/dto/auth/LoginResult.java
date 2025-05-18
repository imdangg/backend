package com.project.imdang.member.domain.dto.auth;

import com.project.imdang.common.domain.valueobject.MemberId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResult {
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
    private boolean isJoined;
    private MemberId memberId;
    private String appleRefreshToken;

    public static LoginResult from(TokenResult response, boolean isJoined, MemberId memberId, String refreshToken) {
        return new LoginResult(response.getAccessToken(), response.getRefreshToken(), response.getExpiresIn(), isJoined, memberId, refreshToken);
    }
}
