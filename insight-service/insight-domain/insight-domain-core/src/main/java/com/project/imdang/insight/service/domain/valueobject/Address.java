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
public class Address {
    private String siDo;            // 시/도 (예: 서울특별시)
    private String siGunGu;         // 시/군/구 (예: 종로구)
    private String eupMyeonDong;    // 읍/면/동 (예: 효제동)
    private String roadName;        // 도로명
    private String buildingNumber;  // 번지 (예: 191)
    private String detail;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(siDo, address.siDo) && Objects.equals(siGunGu, address.siGunGu) && Objects.equals(eupMyeonDong, address.eupMyeonDong) && Objects.equals(roadName, address.roadName) && Objects.equals(buildingNumber, address.buildingNumber) && Objects.equals(detail, address.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(siDo, siGunGu, eupMyeonDong, roadName, buildingNumber, detail);
    }
}
