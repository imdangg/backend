package com.project.imdang.domain.fetch;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FetchMemberResponse {
    private String nickname;
    private int accusedCount;
    private String memberStatus;
    private LocalDate penaltyPeriodFrom;
    private LocalDate penaltyPeriodTo;
}
