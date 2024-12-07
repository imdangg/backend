package com.project.imdang.domain.event.publisher;

import com.project.imdang.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {
    void publish(T domainEvent);
}
