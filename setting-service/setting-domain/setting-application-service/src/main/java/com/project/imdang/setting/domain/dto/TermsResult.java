package com.project.imdang.setting.domain.dto;

import com.project.imdang.common.domain.valueobject.TermsId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TermsResult {
    private TermsId termsId;
    private String title;
    private String url;
    private Boolean isEssential;
}
