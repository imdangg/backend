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
public class Address {
    @Schema(description = "시/도", example = "서울특별시")
    @NotBlank
    private String siDo; // 시/도 (예: 서울특별시)
    @Schema(description = "시/군/구", example = "종로구")
    @NotBlank
    private String siGunGu; // 시/군/구 (예: 종로구)
    @Schema(description = "읍/면/동", example = "효제동")
    @NotBlank
    private String eupMyeonDong; // 읍/면/동 (예: 효제동)
    @Schema(description = "도로명")
    @NotBlank
    private String roadName; // 도로명
    @Schema(description = "번지", example = "191")
    @NotBlank
    private String buildingNumber; // 번지 (예: 191)
    private String detail;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Address address = (Address) o;
        return Objects.equals(siDo, address.siDo) && Objects.equals(siGunGu, address.siGunGu)
                && Objects.equals(eupMyeonDong, address.eupMyeonDong) && Objects.equals(roadName, address.roadName)
                && Objects.equals(buildingNumber, address.buildingNumber) && Objects.equals(detail, address.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(siDo, siGunGu, eupMyeonDong, roadName, buildingNumber, detail);
    }
}
