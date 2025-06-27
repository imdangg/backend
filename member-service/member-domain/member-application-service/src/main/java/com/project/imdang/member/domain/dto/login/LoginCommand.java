package com.project.imdang.member.domain.dto.login;

import com.project.imdang.common.domain.valueobject.OAuthProvider;
import lombok.Builder;

@Builder
public record LoginCommand(
        OAuthProvider provider,
        String identifier
) {
}
