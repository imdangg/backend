package com.project.imdang.insight.service.domain.dto.insight.accuse;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccuseInsightCommand {
    // insightId - accuseMemberId UNIQUE
    @NotNull
    private UUID insightId;

    @Setter
    @NotNull
    private UUID accuseMemberId;    // accusedBy : 신고한 memberId
}
