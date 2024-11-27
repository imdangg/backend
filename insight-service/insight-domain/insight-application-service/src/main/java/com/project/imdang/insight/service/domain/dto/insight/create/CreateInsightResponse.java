package com.project.imdang.insight.service.domain.dto.insight.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class CreateInsightResponse {
    private UUID insightId;
}
