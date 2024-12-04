package com.project.imdang.insight.service.domain.dto.insight.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ListInsightQuery {
    private int page;
    private int size;
    private String sort;
    private String direction;
}
