package com.project.imdang.common.domain.valueobject.living;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 학군
 */
@Getter
@RequiredArgsConstructor
public enum SchoolDistrict {
    ELEMENTARY_APARTMENT("초품아"), PRESTIGIOUS_SCHOOL("명문_초/중/고"), CLOSE_ACADEMY("학원가_근접"),
    BABY_COMMUNITY("육아_커뮤니티_활발"), SAFE_ROUTE("안전한_통학로");
    private final String value;
}
