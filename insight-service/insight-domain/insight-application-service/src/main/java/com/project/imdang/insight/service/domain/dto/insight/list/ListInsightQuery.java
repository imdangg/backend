package com.project.imdang.insight.service.domain.dto.insight.list;

import com.project.imdang.insight.service.domain.valueobject.Address;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ListInsightQuery {
    // 주소/단지별
    private Address address;
    private ApartmentComplex apartmentComplex;
}
