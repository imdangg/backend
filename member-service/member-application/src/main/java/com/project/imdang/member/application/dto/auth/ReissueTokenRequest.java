package com.project.imdang.member.application.dto.auth;

import java.util.UUID;

public record ReissueTokenRequest(UUID memberId, String refreshToken) {
}
