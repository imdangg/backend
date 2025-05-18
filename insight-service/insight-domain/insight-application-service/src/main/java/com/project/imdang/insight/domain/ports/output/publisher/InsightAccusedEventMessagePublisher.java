package com.project.imdang.insight.domain.ports.output.publisher;

import com.project.imdang.common.domain.event.DomainEventMessagePublisher;
import com.project.imdang.insight.messaging.message.InsightAccusedEventMessage;

public interface InsightAccusedEventMessagePublisher extends DomainEventMessagePublisher<InsightAccusedEventMessage> {
}
