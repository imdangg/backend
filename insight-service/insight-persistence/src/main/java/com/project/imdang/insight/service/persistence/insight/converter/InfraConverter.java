package com.project.imdang.insight.service.persistence.insight.converter;

import com.project.imdang.insight.service.domain.valueobject.Infra;
import jakarta.persistence.Converter;

@Converter
public class InfraConverter extends JsonConverter<Infra> {

    public InfraConverter() {
        super(Infra.class);
    }
}
