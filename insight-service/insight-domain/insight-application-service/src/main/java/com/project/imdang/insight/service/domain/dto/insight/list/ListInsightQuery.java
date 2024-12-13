package com.project.imdang.insight.service.domain.dto.insight.list;

import com.project.imdang.domain.dto.PagingQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "검색 조건")
public class ListInsightQuery extends PagingQuery {
}
