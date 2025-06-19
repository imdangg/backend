package com.project.imdang.common.domain.event;

import org.springframework.context.event.EventListener;

public abstract class DomainEventMessageListener<E extends DomainEventMessage> {

    public abstract void process(E domainEventMessage);

    @EventListener
    public void handle(E domainEventMessage) {
        try {
            process(domainEventMessage);
        } catch (Exception e) {
            // TODO - CHECK : 보상 트랜잭션에서 또 에러가 나면?
//            log.error(e.getMessage(), e);
//            DomainRollbackEventMessage domainRollbackEventMessage = domainEventMessage.getRollbackEventMessage(e.getMessage());
//            rollbackEventPublisher.publishEvent(domainRollbackEventMessage);

            throw new RuntimeException("Error!");
        }
    }
}
