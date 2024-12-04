package com.project.imdang.insight.service.persistence.insight.converter;

import com.project.imdang.insight.service.domain.valueobject.ComplexFacility;
import jakarta.persistence.Converter;

@Converter
public class ComplexFacilityConverter extends JsonConverter<ComplexFacility> {

    public ComplexFacilityConverter() {
        super(ComplexFacility.class);
    }
}
