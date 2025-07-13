package com.project.imdang.member.domain.dto.member;

import com.project.imdang.common.domain.valueobject.MemberId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class MemberResult {
    private UUID memberId;
    private String nickname;
    private String birthDate;
    private String gender;
    private String deviceToken;
    private String refreshToken;
    private Long insightCount;
}
