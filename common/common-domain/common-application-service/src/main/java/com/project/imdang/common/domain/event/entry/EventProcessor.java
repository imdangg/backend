package com.project.imdang.common.domain.event.entry;

import com.project.imdang.domain.event.DomainEvent;

public interface EventProcessor {
    <T extends DomainEvent> void process(T event);
}
