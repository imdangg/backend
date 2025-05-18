package com.project.imdang.insight.persistence.converter;

import com.project.imdang.common.domain.valueobject.VisitTime;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class VisitTimeSetConverter extends EnumSetConverter<VisitTime> {

    public VisitTimeSetConverter() {
        super(VisitTime.class);
    }
}
