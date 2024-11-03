package com.project.imdang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class DeleteInsightCommand {
    private final String insightId;
}
