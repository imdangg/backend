package com.project.imdang.member.domain.dto.member;

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
public class JoinCommand {
    private MemberId memberId;
    private String nickname;
    private String birthDate;
    private Gender gender;
    private String deviceToken;
}
