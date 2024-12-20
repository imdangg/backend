package com.project.imdang.insight.service.domain.valueobject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApartmentComplex {
    // TODO - equalsAndHashcode
    private String name;

    // TODO - CHECK
//    private Address address;
}
