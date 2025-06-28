package com.project.imdang.insight.domain.dto.insight.list;

import com.project.imdang.common.domain.dto.PagingQuery;
import com.project.imdang.common.domain.valueobject.District;
import com.project.imdang.common.domain.valueobject.MemberId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ListBookmarkedInsightQuery extends PagingQuery {

    private MemberId memberId;
    private District district;
    private String apartmentComplexName;
    private Boolean onlyMine;

    @Builder
    private ListBookmarkedInsightQuery(Integer pageNumber, Integer pageSize, String direction, String[] properties,
                               MemberId memberId,
                               String siDo, String siGunGu, String eupMyeonDong,
                               String apartmentComplexName, Boolean onlyMine) {
        super(pageNumber, pageSize, direction, properties);
        this.memberId = memberId;
        this.district = new District(siDo, siGunGu, eupMyeonDong, null);
        this.apartmentComplexName = apartmentComplexName;
        this.onlyMine = onlyMine;
    }
}
