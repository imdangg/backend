package com.project.imdang.insight.messaging.publisher;

import com.project.imdang.insight.domain.ports.output.publisher.InsightCreatedEventMessagePublisher;
import com.project.imdang.insight.messaging.message.InsightCreatedEventMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class InsightCreatedEventMessagePublisherImpl implements InsightCreatedEventMessagePublisher {

    private final ApplicationEventPublisher domainEventMessagePublisher;

    @Override
    public void publish(InsightCreatedEventMessage insightCreatedEventMessage) {
        domainEventMessagePublisher.publishEvent(insightCreatedEventMessage);
    }
}
