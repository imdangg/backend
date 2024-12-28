package com.project.imdang.insight.service.application.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ApartmentComplexApiResponse {

    private Integer page;
    private Integer perPage;
    private Integer totalCount;
    private Integer currentCount;
    private Integer matchCount;
    private List<Data> data;

    @Getter
    @NoArgsConstructor
    public static class Data {
        @JsonProperty("COMPLEX_PK")
        private String key;
        @JsonProperty("ADRES")
        private String address;
        @JsonProperty("COMPLEX_NM1")
        private String complexName;
    }
}
