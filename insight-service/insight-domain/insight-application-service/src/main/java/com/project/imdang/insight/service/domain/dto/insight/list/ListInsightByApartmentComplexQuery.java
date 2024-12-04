package com.project.imdang.insight.service.domain.dto.insight.list;

import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ListInsightByApartmentComplexQuery {

    private ApartmentComplex apartmentComplex;

    private int page;
    private int size;
    private String sort;
    private String direction;
}
