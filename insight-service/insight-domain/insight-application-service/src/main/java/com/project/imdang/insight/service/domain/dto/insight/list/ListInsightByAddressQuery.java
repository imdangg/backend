package com.project.imdang.insight.service.domain.dto.insight.list;

import com.project.imdang.domain.dto.PagingQuery;
import com.project.imdang.insight.service.domain.valueobject.Address;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListInsightByAddressQuery extends PagingQuery {
    // 주소/단지별
    private String siGunGu;
    private String dong;
}
