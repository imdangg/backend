package com.project.imdang.insight.domain.dto.insight.list;

import com.project.imdang.common.domain.dto.PagingQuery;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ListInsightByDateQuery extends PagingQuery {
    private LocalDate date;

    @Builder
    private ListInsightByDateQuery(Integer pageNumber,
                                   Integer pageSize,
                                   String direction,
                                   String[] properties,
                                   LocalDate date) {
        super(pageNumber, pageSize, direction, properties);
        this.date = date;
    }
}
