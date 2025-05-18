package com.project.imdang.setting.domain.ports.output.repository;

import com.project.imdang.setting.domain.entity.TermsAgreement;

public interface TermsAgreementRepository {
    TermsAgreement save(TermsAgreement termsAgreement);
}
