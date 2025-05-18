package com.project.imdang.common.domain.event.entry;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class EventEntry {
    private final String type;
    private final String contentType;
    private final String payload;
    private final ZonedDateTime dateTime;

    @Builder
    public EventEntry(String type, String contentType, String payload) {
        this.type = type;
        this.contentType = contentType;
        this.payload = payload;
        this.dateTime = ZonedDateTime.now();
    }
}
