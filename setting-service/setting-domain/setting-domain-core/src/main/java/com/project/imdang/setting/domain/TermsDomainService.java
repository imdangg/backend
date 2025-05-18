package com.project.imdang.setting.domain;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.setting.domain.entity.Terms;
import com.project.imdang.setting.domain.entity.TermsAgreement;

public interface TermsDomainService {
    TermsAgreement agreeTerms(Terms terms, MemberId memberId);
}
