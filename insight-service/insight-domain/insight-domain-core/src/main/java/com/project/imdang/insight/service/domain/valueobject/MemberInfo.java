package com.project.imdang.insight.service.domain.valueobject;

import com.project.imdang.domain.valueobject.MemberId;
import lombok.Builder;

// TODO - CHECK : 위치
@Builder
public record MemberInfo(MemberId memberId,
                         String nickname,
                         String birthDate,
                         String gender,
                         String deviceToken,
                         int accusedCount,
                         int exchangeCount,
                         int insightCount,
                         int rejectedCount) {
}
