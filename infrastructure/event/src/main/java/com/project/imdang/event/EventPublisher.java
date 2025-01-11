package com.project.imdang.event;

import com.project.imdang.domain.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EventPublisher {
// 외부에서 이벤트를 받아 저장소에 추가
    private final EventManager eventManager;

    public <T extends DomainEvent> void publish(T domainEvent) {
        eventManager.save(domainEvent);
    }
}
