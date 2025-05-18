package com.project.imdang.insight.domain.dto.insight.list;

import com.project.imdang.common.domain.dto.PagingQuery;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ListInsightByDistrictQuery extends PagingQuery {

    private String siDo;
    private String siGunGu;
    private String eupMyeonDong;

    @Builder
    private ListInsightByDistrictQuery(Integer pageNumber,
                                       Integer pageSize,
                                       String direction,
                                       String[] properties,
                                       String siDo, String siGunGu, String eupMyeonDong) {
        super(pageNumber, pageSize, direction, properties);
        this.siDo = siDo;
        this.siGunGu = siGunGu;
        this.eupMyeonDong = eupMyeonDong;
    }
}
