package com.project.imdang.insight.persistence.converter;

import com.project.imdang.common.domain.valueobject.VisitMethod;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class VisitMethodSetConverter extends EnumSetConverter<VisitMethod> {

    public VisitMethodSetConverter() {
        super(VisitMethod.class);
    }
}


