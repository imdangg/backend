package com.project.imdang.setting.service.persistence.terms.adapter;

import com.project.imdang.setting.service.domain.entity.TermsAgreement;
import com.project.imdang.setting.service.domain.ports.output.repository.TermsAgreementRepository;
import com.project.imdang.setting.service.persistence.terms.entity.TermsAgreementEntity;
import com.project.imdang.setting.service.persistence.terms.mapper.TermsAgreementPersistenceMapper;
import com.project.imdang.setting.service.persistence.terms.repository.TermsAgreementJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TermsAgreementRepositoryImpl implements TermsAgreementRepository {

    private final TermsAgreementJpaRepository termsAgreementJpaRepository;
    private final TermsAgreementPersistenceMapper termsAgreementPersistenceMapper;

    @Override
    public TermsAgreement save(TermsAgreement termsAgreement) {
        TermsAgreementEntity termsAgreementEntity = termsAgreementPersistenceMapper.termsAgreementToTermsAgreementEntity(termsAgreement);
        TermsAgreementEntity saved = termsAgreementJpaRepository.save(termsAgreementEntity);
        return termsAgreementPersistenceMapper.termsAgreementEntityToTermsAgreement(saved);
    }
}
