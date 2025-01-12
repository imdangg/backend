package com.project.imdang.event;

import com.project.imdang.domain.event.DomainEvent;

import java.util.List;

public interface EventManager {
    <T extends DomainEvent> void save(T event);
    List<EventEntry> get(long offset, long limit);
}
