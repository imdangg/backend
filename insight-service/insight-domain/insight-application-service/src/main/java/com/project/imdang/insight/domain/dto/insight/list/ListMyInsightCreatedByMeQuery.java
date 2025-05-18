package com.project.imdang.insight.domain.dto.insight.list;

import com.project.imdang.common.domain.valueobject.MemberId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListMyInsightCreatedByMeQuery {

    private MemberId memberId;

    private Integer pageNumber;
    private Integer pageSize;
    private String direction;
    private String[] properties;
}
