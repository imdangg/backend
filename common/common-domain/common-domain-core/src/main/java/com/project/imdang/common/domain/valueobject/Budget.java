package com.project.imdang.common.domain.valueobject;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Budget {
    UNDER_100M("1억 이하"), UNDER_300M("3억 이하"), UNDER_500M("5억 이하"), UNDER_700M("7억 이하"), UNDER_900M("9억 이하"),
    UNDER_1500M("15억 이하"), UNDER_2000M("20억 이하"), UNDER_3000M("30억 이하"), UNDER_5000M("50억 이하");

    private final String value;
}
