package com.project.imdang.setting.service.domain.valueobject;

import com.project.imdang.domain.valueobject.MemberId;
import lombok.Builder;

@Builder
public record MemberInfo(MemberId memberId,
                         String deviceToken) {
}
