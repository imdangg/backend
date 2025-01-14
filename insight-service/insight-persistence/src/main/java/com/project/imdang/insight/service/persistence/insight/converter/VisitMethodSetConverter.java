package com.project.imdang.insight.service.persistence.insight.converter;

import com.project.imdang.insight.service.domain.valueobject.VisitMethod;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class VisitMethodSetConverter extends EnumSetConverter<VisitMethod> {

    public VisitMethodSetConverter() {
        super(VisitMethod.class);
    }
}


