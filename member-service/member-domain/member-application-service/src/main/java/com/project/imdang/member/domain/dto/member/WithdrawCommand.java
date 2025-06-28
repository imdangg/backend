package com.project.imdang.member.domain.dto.member;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.OAuthProvider;
import lombok.Builder;

@Builder
public record WithdrawCommand(
        MemberId memberId,
        OAuthProvider oAuthProvider,
        String identifier
) {
}
