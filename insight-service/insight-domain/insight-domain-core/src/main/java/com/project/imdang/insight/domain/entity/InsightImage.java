package com.project.imdang.insight.domain.entity;

import com.project.imdang.common.domain.valueobject.InsightId;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class InsightImage {

    private final InsightId insightId;
    private final int type;
    private final int sortNum;
    private final String image;
    private final ZonedDateTime createdAt;

    @Builder
    public InsightImage(InsightId insightId, int type, int sortNum, String image, ZonedDateTime createdAt) {
        this.insightId = insightId;
        this.type = type;
        this.sortNum = sortNum;
        this.image = image;
        this.createdAt = createdAt;
    }

    static InsightImage createNewInsightImage(InsightId insightId, int type, int sortNum, String image) {
        return InsightImage.builder()
                .insightId(insightId)
                .type(type)
                .sortNum(sortNum)
                .image(image)
                .createdAt(ZonedDateTime.now())
                .build();
    }
}
