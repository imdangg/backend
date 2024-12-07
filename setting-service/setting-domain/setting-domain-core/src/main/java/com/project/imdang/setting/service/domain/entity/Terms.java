package com.project.imdang.setting.service.domain.entity;

import com.project.imdang.domain.entity.AggregateRoot;
import com.project.imdang.setting.service.domain.valueobject.TermsId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Terms extends AggregateRoot<TermsId> {
// TODO - CHECK : AggregateRoot vs BaseEntity
    private final String title;
    private String contents;

//    @Builder
    public Terms(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
