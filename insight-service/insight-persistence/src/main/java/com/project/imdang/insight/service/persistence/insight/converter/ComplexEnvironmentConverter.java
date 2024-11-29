package com.project.imdang.insight.service.persistence.insight.converter;

import com.project.imdang.insight.service.domain.valueobject.ComplexEnvironment;
import jakarta.persistence.Converter;

@Converter
public class ComplexEnvironmentConverter extends JsonConverter<ComplexEnvironment> {

    public ComplexEnvironmentConverter() {
        super(ComplexEnvironment.class);
    }
}
