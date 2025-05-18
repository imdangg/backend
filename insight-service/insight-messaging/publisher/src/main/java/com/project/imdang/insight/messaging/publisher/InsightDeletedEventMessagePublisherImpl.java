package com.project.imdang.insight.messaging.publisher;

import com.project.imdang.insight.domain.ports.output.publisher.InsightDeletedEventMessagePublisher;
import com.project.imdang.insight.messaging.message.InsightDeletedEventMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class InsightDeletedEventMessagePublisherImpl implements InsightDeletedEventMessagePublisher {

    private final ApplicationEventPublisher domainEventMessagePublisher;

    @Override
    public void publish(InsightDeletedEventMessage insightDeletedEventMessage) {
        domainEventMessagePublisher.publishEvent(insightDeletedEventMessage);
    }
}
