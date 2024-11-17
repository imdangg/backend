package com.project.imdang.insight.service.domain.dto.insight.delete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class DeleteInsightCommand {
    private final String insightId;
}
