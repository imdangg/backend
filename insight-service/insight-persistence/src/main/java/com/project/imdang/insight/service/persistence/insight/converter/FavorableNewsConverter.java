package com.project.imdang.insight.service.persistence.insight.converter;

import com.project.imdang.insight.service.domain.valueobject.FavorableNews;
import jakarta.persistence.Converter;

@Converter
public class FavorableNewsConverter extends JsonConverter<FavorableNews> {

    public FavorableNewsConverter() {
        super(FavorableNews.class);
    }
}
