package com.project.imdang.setting.persistence.adapter;

import com.project.imdang.setting.domain.entity.TermsAgreement;
import com.project.imdang.setting.domain.ports.output.repository.TermsAgreementRepository;
import com.project.imdang.setting.persistence.repository.TermsAgreementJpaRepository;
import com.project.imdang.setting.persistence.entity.TermsAgreementEntity;
import com.project.imdang.setting.persistence.mapper.TermsAgreementPersistenceMapper;
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
