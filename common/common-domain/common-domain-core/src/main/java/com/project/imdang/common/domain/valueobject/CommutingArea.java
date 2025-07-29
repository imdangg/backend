package com.project.imdang.common.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.project.imdang.common.domain.valueobject.gapInvestment.InvestmentPlan;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 출퇴근 지역
 */
@Getter
@RequiredArgsConstructor
public enum CommutingArea {
    GANGNAM("강남"), YEOUIDO("여의도"), GWANGHWAMUN("광화문"), EULJIRO("을지로"), SEONGSU("성수"),
    PANGYO("판교"), MAPO("마포"), GURO("구로"), SANGAM("상암DMC"), GANGSEO("강서"), SONGPA("송파");

    private final String value;

    @JsonCreator
    public static CommutingArea fromValue(String value) {
        return Arrays.stream(CommutingArea.values())
                .filter(ca -> ca.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
    }

    @JsonValue
    public String toValue() {
        return value;
    }
}
