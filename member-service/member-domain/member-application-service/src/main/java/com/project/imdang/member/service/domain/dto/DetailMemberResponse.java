package com.project.imdang.member.service.domain.dto;

import com.project.imdang.member.service.domain.valueobject.Gender;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class DetailMemberResponse {
    private String nickname;
    private String birthDate;
    private Gender gender;
    private String deviceToken;

    private int exchangeCount;
    private int insightCount;
    private int rejectedCount;
}
