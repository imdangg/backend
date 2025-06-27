package com.project.imdang.member.application.dto.login;

import java.util.UUID;

public record ReissueTokenRequest(
        UUID memberId,
        String refreshToken) {
}
