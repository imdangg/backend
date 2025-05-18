package com.project.imdang.member.domain.dto.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReissueTokenResult {
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
}
