package com.project.imdang.setting.domain.mapper;

import com.project.imdang.setting.domain.dto.TermsResult;
import com.project.imdang.setting.domain.entity.Terms;
import org.springframework.stereotype.Component;

@Component
public class TermsDataMapper {

    public TermsResult termsToTermsResult(Terms terms) {
        return TermsResult.builder()
                .termsId(terms.getId().getValue())
                .title(terms.getTitle())
                .url(terms.getUrl())
                .isEssential(terms.getIsEssential())
                .build();
    }
}
