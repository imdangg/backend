package com.project.imdang.setting.service.domain.entity;

import com.project.imdang.domain.entity.BaseEntity;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.setting.service.domain.valueobject.TermsAgreementId;
import com.project.imdang.setting.service.domain.valueobject.TermsId;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class TermsAgreement extends BaseEntity<TermsAgreementId> {

    private final TermsId termsId;
    private final MemberId memberId;
    private final ZonedDateTime createdAt;

    @Builder
    public TermsAgreement(TermsAgreementId id, TermsId termsId, MemberId memberId, ZonedDateTime createdAt) {
        setId(id);
        this.termsId = termsId;
        this.memberId = memberId;
        this.createdAt = createdAt;
    }

    static TermsAgreement createNewTermsAgreement(TermsId termsId, MemberId memberId) {
        return TermsAgreement.builder()
                .termsId(termsId)
                .memberId(memberId)
                .createdAt(ZonedDateTime.now())
                .build();
    }
}
