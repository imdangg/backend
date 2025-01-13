package com.project.imdang.insight.service.persistence.insight.converter;

import com.project.imdang.insight.service.domain.valueobject.VisitTime;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class VisitTimeSetConverter extends EnumSetConverter<VisitTime> {

    public VisitTimeSetConverter() {
        super(VisitTime.class);
    }
}
