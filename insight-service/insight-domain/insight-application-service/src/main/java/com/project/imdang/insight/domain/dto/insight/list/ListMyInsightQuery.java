package com.project.imdang.insight.domain.dto.insight.list;

import com.project.imdang.common.domain.dto.PagingQuery;
import com.project.imdang.common.domain.valueobject.District;
import com.project.imdang.common.domain.valueobject.MemberId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ListMyInsightQuery extends PagingQuery {

    private MemberId memberId;

    private District district;
//    private String siDo;
//    private String siGunGu;
//    private String eupMyeonDong;

    private String apartmentComplexName;
    private Boolean onlyMine;

    @Builder
    private ListMyInsightQuery(Integer pageNumber, Integer pageSize, String direction, String[] properties,
                               MemberId memberId,
                               String siDo, String siGunGu, String eupMyeonDong,
                               String apartmentComplexName, Boolean onlyMine) {
        super(pageNumber, pageSize, direction, properties);
        this.memberId = memberId;
//        this.siDo = siDo;
//        this.siGunGu = siGunGu;
//        this.eupMyeonDong = eupMyeonDong;
        this.district = new District(siDo, siGunGu, eupMyeonDong, null);
        this.apartmentComplexName = apartmentComplexName;
        this.onlyMine = onlyMine;
    }
}
