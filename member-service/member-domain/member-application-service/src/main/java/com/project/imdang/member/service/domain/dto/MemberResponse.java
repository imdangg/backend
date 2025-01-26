package com.project.imdang.member.service.domain.dto;

import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class MemberResponse {
    private UUID memberId;
    private String nickname;
    private String birthDate;
    private String gender;
    private String deviceToken;

    private int exchangeCount;
    private int insightCount;
    private int rejectedCount;
}
