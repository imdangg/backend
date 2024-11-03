package com.project.imdang.event.publisher;

import com.project.imdang.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {
    void publish(T domainEvent);
}
