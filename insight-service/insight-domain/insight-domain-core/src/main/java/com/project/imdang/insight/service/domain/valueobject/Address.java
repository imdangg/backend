package com.project.imdang.insight.service.domain.valueobject;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class Address {
    @Schema(description = "시군구", example = "서울시 서대문구")
    private String siGunGu;
    @Schema(description = "동", example = "임당동")
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
