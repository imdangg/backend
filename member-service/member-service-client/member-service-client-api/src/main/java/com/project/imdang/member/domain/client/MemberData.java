package com.project.imdang.member.domain.client;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberData {
    private UUID memberId;
    private String nickname;
    private String birthDate;
    private String gender;
    private String deviceToken;
    private Long accusedCount;
    private LocalDate latestInsightCreateDate;
}
