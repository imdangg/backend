package com.project.imdang.common.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class District {

    private String siDo; // 시/도 (예: 서울특별시)
    private String siGunGu; // 시/군/구 (예: 종로구)
    private String eupMyeonDong; // 읍/면/동 (예: 효제동)
    private String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        District district = (District) o;
        return Objects.equals(siDo, district.siDo) && Objects.equals(siGunGu, district.siGunGu) && Objects.equals(eupMyeonDong, district.eupMyeonDong) && Objects.equals(code, district.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(siDo, siGunGu, eupMyeonDong, code);
    }
}
