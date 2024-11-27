package com.project.imdang.insight.service.domain.dto.insight.evaluate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ValidateAndEvaluateInsightResponse {
    private int score;
}
