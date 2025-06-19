package com.project.imdang.setting.persistence.mapper;

import com.project.imdang.setting.domain.entity.Terms;
import com.project.imdang.common.domain.valueobject.TermsId;
import com.project.imdang.setting.persistence.entity.TermsEntity;
import org.springframework.stereotype.Component;

@Component
public class TermsPersistenceMapper {

    public TermsEntity termsToTermsEntity(Terms terms) {
        return TermsEntity.builder()
                .id(terms.getId().getValue())
                .title(terms.getTitle())
                .url(terms.getUrl())
                .isEssential(terms.getIsEssential())
                .build();
    }

    public Terms termsEntityToTerms(TermsEntity termsEntity) {
        return Terms.builder()
                .id(new TermsId(termsEntity.getId()))
                .title(termsEntity.getTitle())
                .url(termsEntity.getUrl())
                .isEssential(termsEntity.getIsEssential())
                .build();
    }
}
