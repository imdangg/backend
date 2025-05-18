package com.project.imdang.common.domain.event.entry;

public interface EventListener {
    void process(EventEntry eventEntry);
}
