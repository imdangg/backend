package com.project.imdang.insight.service.domain.dto.insight.list;

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
public class ListInsightByApartmentComplexQuery {
    @NotBlank
    private String apartmentComplexName;

    private Integer pageNumber;
    private Integer pageSize;
    private String direction;
    private String[] properties;
}
