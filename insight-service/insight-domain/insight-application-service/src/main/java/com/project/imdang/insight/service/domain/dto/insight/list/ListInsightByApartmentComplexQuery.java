package com.project.imdang.insight.service.domain.dto.insight.list;

import com.project.imdang.domain.dto.PagingQuery;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListInsightByApartmentComplexQuery extends PagingQuery {

    // TODO - apartmentComplexKey로 변경
    @NotBlank
    private String apartmentComplexName;
}
