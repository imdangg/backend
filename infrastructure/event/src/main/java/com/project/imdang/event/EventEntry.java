package com.project.imdang.event;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EventEntry {
    private Long id;
    private final String type;
    private final String contentType;
    private final String payload;
    private final long timestamp;

    public EventEntry(Long id, String type, String contentType, String payload, long timestamp) {
        this.id = id;
        this.type = type;
        this.contentType = contentType;
        this.payload = payload;
        this.timestamp = timestamp;
    }

    @Builder
    public EventEntry(String type, String contentType, String payload) {
        this.type = type;
        this.contentType = contentType;
        this.payload = payload;
        this.timestamp = System.currentTimeMillis();
    }
}
