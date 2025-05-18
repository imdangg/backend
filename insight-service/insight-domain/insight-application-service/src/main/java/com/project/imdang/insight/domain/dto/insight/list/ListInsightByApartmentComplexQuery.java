package com.project.imdang.insight.domain.dto.insight.list;

import com.project.imdang.common.domain.dto.PagingQuery;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ListInsightByApartmentComplexQuery extends PagingQuery {
    private String apartmentComplexName;

    @Builder
    private ListInsightByApartmentComplexQuery(Integer pageNumber,
                                               Integer pageSize,
                                               String direction,
                                               String[] properties,
                                               String apartmentComplexName) {
        super(pageNumber, pageSize, direction, properties);
        this.apartmentComplexName = apartmentComplexName;
    }
}
