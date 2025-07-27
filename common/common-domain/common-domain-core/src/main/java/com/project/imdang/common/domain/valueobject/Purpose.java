package com.project.imdang.common.domain.valueobject;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Purpose {
    LIVING("실거주"), GAP_INVESTMENT("갭투자");
    private final String value;
}
