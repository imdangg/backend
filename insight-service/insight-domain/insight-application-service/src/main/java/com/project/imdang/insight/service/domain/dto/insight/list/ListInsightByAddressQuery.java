package com.project.imdang.insight.service.domain.dto.insight.list;

import com.project.imdang.insight.service.domain.valueobject.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ListInsightByAddressQuery {
    // 주소/단지별
    private Address address;

    private int page;
    private int size;
    private String sort;
    private String direction;
}
