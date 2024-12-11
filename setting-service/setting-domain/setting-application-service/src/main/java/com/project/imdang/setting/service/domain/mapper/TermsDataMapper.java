package com.project.imdang.setting.service.domain.mapper;

import com.project.imdang.setting.service.domain.dto.TermsResponse;
import com.project.imdang.setting.service.domain.entity.Terms;
import org.springframework.stereotype.Component;

@Component
public class TermsDataMapper {

    public TermsResponse termsToTermsResponse(Terms terms) {
        return TermsResponse.builder()
                .termsId(terms.getId().getValue())
                .title(terms.getTitle())
                .url(terms.getUrl())
                .isEssential(terms.getIsEssential())
                .build();
    }
}
