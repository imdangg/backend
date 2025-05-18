package com.project.imdang.insight.domain.dto.insight.list;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApartmentComplexResult {
    private String name;

    public ApartmentComplexResult(String name) {
        this.name = name;
    }
}
