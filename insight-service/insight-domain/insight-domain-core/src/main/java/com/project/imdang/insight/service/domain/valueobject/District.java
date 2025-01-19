package com.project.imdang.insight.service.domain.valueobject;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
public class District {

    @Schema(description = "시/도", example = "서울특별시")
    @NotBlank
    private String siDo; // 시/도 (예: 서울특별시)
    @Schema(description = "시/군/구", example = "종로구")
    @NotBlank
    private String siGunGu; // 시/군/구 (예: 종로구)
    @Schema(description = "읍/면/동", example = "효제동")
    @NotBlank
    private String eupMyeonDong; // 읍/면/동 (예: 효제동)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        District district = (District) o;
        return Objects.equals(siDo, district.siDo) && Objects.equals(siGunGu, district.siGunGu) && Objects.equals(eupMyeonDong, district.eupMyeonDong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(siDo, siGunGu, eupMyeonDong);
    }
}
