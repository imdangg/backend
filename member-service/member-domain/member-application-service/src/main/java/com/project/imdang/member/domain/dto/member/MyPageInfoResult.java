package com.project.imdang.member.domain.dto.member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MyPageInfoResult {
    private String nickname;
    private Integer insightCount;
    private Integer requestCount;
}
