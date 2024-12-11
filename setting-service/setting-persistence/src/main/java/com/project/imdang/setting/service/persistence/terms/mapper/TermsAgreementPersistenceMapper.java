package com.project.imdang.setting.service.persistence.terms.mapper;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.setting.service.domain.entity.TermsAgreement;
import com.project.imdang.setting.service.domain.valueobject.TermsAgreementId;
import com.project.imdang.setting.service.domain.valueobject.TermsId;
import com.project.imdang.setting.service.persistence.terms.entity.TermsAgreementEntity;
import org.springframework.stereotype.Component;

@Component
public class TermsAgreementPersistenceMapper {

    public TermsAgreementEntity termsAgreementToTermsAgreementEntity(TermsAgreement termsAgreement) {
        return TermsAgreementEntity.builder()
                .id(termsAgreement.getId().getValue())
                .termsId(termsAgreement.getTermsId().getValue())
                .memberId(termsAgreement.getMemberId().getValue())
                .createdAt(termsAgreement.getCreatedAt())
                .build();
    }

    public TermsAgreement termsAgreementEntityToTermsAgreement(TermsAgreementEntity termsAgreementEntity) {
        return TermsAgreement.builder()
                .id(new TermsAgreementId(termsAgreementEntity.getId()))
                .termsId(new TermsId(termsAgreementEntity.getTermsId()))
                .memberId(new MemberId(termsAgreementEntity.getMemberId()))
                .createdAt(termsAgreementEntity.getCreatedAt())
                .build();
    }
}
