package com.project.imdang.setting.service.domain.entity;

import com.project.imdang.setting.service.domain.valueobject.TermsId;

public class Terms {
    private TermsId id;
    private final String title;
    private String contents;
    private String link;

    public Terms(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    // TODO : 생성자 (title, link)
}
