package com.project.imdang.insight.service.domain.valueobject;

import lombok.Builder;

import java.util.UUID;

// TODO - CHECK : 위치
@Builder
public record MemberInfo(UUID memberId,
                         String nickname,
                         String birthDate,
                         String gender,
                         String deviceToken,
                         int accusedCount,
                         int exchangeCount,
                         int insightCount,
                         int rejectedCount) {
}
