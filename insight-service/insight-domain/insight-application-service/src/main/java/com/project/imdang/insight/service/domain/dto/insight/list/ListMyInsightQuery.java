package com.project.imdang.insight.service.domain.dto.insight.list;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListMyInsightQuery {
        // extends PagingQuery {
//    @Setter
    private UUID memberId;
    private String apartmentComplexName;

    private Boolean onlyMine;

    private Integer pageNumber;
    private Integer pageSize;
    private String direction;
    private String[] properties;
}
