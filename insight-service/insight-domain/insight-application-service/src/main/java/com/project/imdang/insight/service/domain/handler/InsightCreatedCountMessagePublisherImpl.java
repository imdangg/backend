package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.message.InsightCreatedCountRequestMessage;
import com.project.imdang.insight.service.domain.ports.output.publisher.InsightCreatedCountMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InsightCreatedCountMessagePublisherImpl implements InsightCreatedCountMessagePublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(InsightCreatedCountRequestMessage insightCreatedCountRequestMessage) {
        applicationEventPublisher.publishEvent(insightCreatedCountRequestMessage);
    }
}
