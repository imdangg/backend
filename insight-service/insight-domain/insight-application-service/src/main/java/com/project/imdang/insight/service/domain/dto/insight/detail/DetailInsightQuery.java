package com.project.imdang.insight.service.domain.dto.insight.detail;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DetailInsightQuery {
    @NotNull
    private UUID insightId;

//    @Setter
    private UUID memberId;  // requestedBy
}
