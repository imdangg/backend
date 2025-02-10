package com.project.imdang.insight.service.domain.ports.output.publisher;

import com.project.imdang.domain.message.InsightCreatedCountRequestMessage;

public interface InsightCreatedCountMessagePublisher {
    void publish(InsightCreatedCountRequestMessage insightCreatedCountRequestMessage);
}
