package com.project.imdang.member.domain.dto.auth;

import com.project.imdang.common.domain.valueobject.Gender;
import com.project.imdang.common.domain.valueobject.MemberId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JoinMemberCommand {
    private MemberId memberId;
    private String nickname;
    private String birthDate;
    private Gender gender;
    private String deviceToken;
}
