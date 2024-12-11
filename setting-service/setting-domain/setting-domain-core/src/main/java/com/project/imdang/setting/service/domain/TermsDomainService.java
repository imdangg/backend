package com.project.imdang.setting.service.domain;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.setting.service.domain.entity.Terms;
import com.project.imdang.setting.service.domain.entity.TermsAgreement;

public interface TermsDomainService {
    TermsAgreement agreeTerms(Terms terms, MemberId memberId);
}
