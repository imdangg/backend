package com.project.imdang.setting.service.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TermsResponse {
    @Schema(description = "약관 ID")
    private Long termsId;
    @Schema(description = "제목")
    private String title;
    @Schema(description = "약관 URL")
    private String url;
    @Schema(description = "필수 동의 여부")
    private Boolean isEssential;
}
