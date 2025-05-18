package com.project.imdang.insight.messaging.publisher;

import com.project.imdang.insight.domain.ports.output.publisher.InsightAccusedEventMessagePublisher;
import com.project.imdang.insight.messaging.message.InsightAccusedEventMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class InsightAccusedEventMessagePublisherImpl implements InsightAccusedEventMessagePublisher {

    private final ApplicationEventPublisher domainEventMessagePublisher;

    @Override
    public void publish(InsightAccusedEventMessage insightAccusedEventMessage) {
        domainEventMessagePublisher.publishEvent(insightAccusedEventMessage);
    }
}
