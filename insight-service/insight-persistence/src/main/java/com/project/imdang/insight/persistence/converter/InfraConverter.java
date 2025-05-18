package com.project.imdang.insight.persistence.converter;

import com.project.imdang.common.domain.valueobject.Infra;
import jakarta.persistence.Converter;

@Converter
public class InfraConverter extends JsonConverter<Infra> {

    public InfraConverter() {
        super(Infra.class);
    }
}
