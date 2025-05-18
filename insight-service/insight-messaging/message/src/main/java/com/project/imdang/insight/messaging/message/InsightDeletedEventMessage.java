package com.project.imdang.insight.messaging.message;

import com.project.imdang.common.domain.event.DomainEventMessage;

import java.util.UUID;

public record InsightDeletedEventMessage(
        UUID insightId,
        UUID memberId) implements DomainEventMessage {
}
