package com.project.imdang.insight.persistence.converter;

import com.project.imdang.common.domain.valueobject.ComplexEnvironment;
import jakarta.persistence.Converter;

@Converter
public class ComplexEnvironmentConverter extends JsonConverter<ComplexEnvironment> {

    public ComplexEnvironmentConverter() {
        super(ComplexEnvironment.class);
    }
}
