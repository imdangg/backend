package com.project.imdang.setting.service.domain.entity;

import com.project.imdang.domain.entity.AggregateRoot;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.setting.service.domain.valueobject.TermsId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Terms extends AggregateRoot<TermsId> {
// TODO - CHECK : AggregateRoot vs BaseEntity
    private final String title;
    private final String url;
    private final Boolean isEssential;

    @Builder
    public Terms(TermsId id, String title, String url, Boolean isEssential) {
        setId(id);
        this.title = title;
        this.url = url;
        this.isEssential = isEssential;
    }

    public TermsAgreement agree(MemberId agreedBy) {
        return TermsAgreement.createNewTermsAgreement(getId(), agreedBy);
    }
}
