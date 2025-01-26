package com.project.imdang.setting.service.domain.valueobject;

import lombok.Builder;

import java.util.UUID;

@Builder
public record MemberInfo(UUID memberId,
                         String deviceToken) {
}
