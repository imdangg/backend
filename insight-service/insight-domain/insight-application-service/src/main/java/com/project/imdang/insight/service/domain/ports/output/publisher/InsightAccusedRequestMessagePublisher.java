package com.project.imdang.insight.service.domain.ports.output.publisher;

import com.project.imdang.domain.message.InsightAccusedRequestMessage;

public interface InsightAccusedRequestMessagePublisher {
    void publish(InsightAccusedRequestMessage insightAccusedRequestMessage);
}
