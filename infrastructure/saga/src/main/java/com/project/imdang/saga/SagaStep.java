package com.project.imdang.saga;

import com.project.imdang.domain.event.DomainEvent;

public interface SagaStep<T, S extends DomainEvent> {
    S process(T data);
    void rollback(T data);
}
