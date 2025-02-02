package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.message.InsightAccusedRequestMessage;
import com.project.imdang.insight.service.domain.ports.output.publisher.InsightAccusedRequestMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InsightAccusedRequestMessagePublisherImpl implements InsightAccusedRequestMessagePublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(InsightAccusedRequestMessage insightAccusedRequestMessage) {
        applicationEventPublisher.publishEvent(insightAccusedRequestMessage);
    }
}
