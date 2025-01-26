package com.project.imdang.feign;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberInfoResponse {
    private UUID memberId;
    private String nickname;
    private String birthDate;
    private String gender;
    private String deviceToken;

    private int accusedCount;
    private int exchangeCount;
    private int insightCount;
    private int rejectedCount;
}
