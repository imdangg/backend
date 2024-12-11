package com.project.imdang.setting.service.domain;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.setting.service.domain.entity.Terms;
import com.project.imdang.setting.service.domain.entity.TermsAgreement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TermsDomainServiceImpl implements TermsDomainService {

    @Override
    public TermsAgreement agreeTerms(Terms terms, MemberId memberId) {
        TermsAgreement termsAgreement = terms.agree(memberId);
        log.info("Terms[id: {}] is agreed by Member[id: {}].", termsAgreement.getTermsId().getValue(), termsAgreement.getMemberId().getValue());
        return termsAgreement;
    }
}
