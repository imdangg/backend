package com.project.imdang.insight.domain.dto.insight.list;

import com.project.imdang.common.domain.dto.PagingQuery;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ListInsightQuery extends PagingQuery {

    @Builder
    private ListInsightQuery(Integer pageNumber,
                             Integer pageSize,
                             String direction,
                             String[] properties) {
        super(pageNumber, pageSize, direction, properties);
    }
}
