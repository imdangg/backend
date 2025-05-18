package com.project.imdang.common.domain.event;

public interface DomainEventMessagePublisher<E extends DomainEventMessage> {
    void publish(E domainEventMessage);
}
