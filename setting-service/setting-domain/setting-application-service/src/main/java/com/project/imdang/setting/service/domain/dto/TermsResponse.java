package com.project.imdang.setting.service.domain.dto;

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
    private Long termsId;
    private String title;
    private String url;
    private Boolean isEssential;
}
