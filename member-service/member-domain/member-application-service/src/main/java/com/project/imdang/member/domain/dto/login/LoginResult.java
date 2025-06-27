package com.project.imdang.member.domain.dto.login;

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
public class LoginResult {
    private UUID memberId;
    private Boolean isJoined;

    private String accessToken;
    private String refreshToken;
}
