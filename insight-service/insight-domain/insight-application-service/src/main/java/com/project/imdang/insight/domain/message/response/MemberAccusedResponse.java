package com.project.imdang.insight.domain.message.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MemberAccusedResponse {
    private boolean isCompleted;
    private UUID accusedMemberId;
}
