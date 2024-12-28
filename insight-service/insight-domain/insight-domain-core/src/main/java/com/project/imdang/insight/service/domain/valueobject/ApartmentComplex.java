package com.project.imdang.insight.service.domain.valueobject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApartmentComplex {

    private String name;
    private String key;

    // TODO - CHECK
//    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApartmentComplex that = (ApartmentComplex) o;
        return Objects.equals(name, that.name) && Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, key);
    }
}
