package com.project.imdang.insight.service.domain.valueobject;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Address {
    private String siGunGu;
    private String dong;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(siGunGu, address.siGunGu) && Objects.equals(dong, address.dong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(siGunGu, dong);
    }
}
