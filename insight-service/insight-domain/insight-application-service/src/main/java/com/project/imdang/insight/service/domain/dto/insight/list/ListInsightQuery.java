package com.project.imdang.insight.service.domain.dto.insight.list;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "검색 조건")
public class ListInsightQuery {
    private Integer pageNumber;
    private Integer pageSize;
    private String direction;
    private String[] properties;
}
