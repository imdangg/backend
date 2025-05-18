package com.project.imdang.insight.messaging.message;

import com.project.imdang.common.domain.event.DomainEventMessage;

import java.util.UUID;

public record InsightAccusedEventMessage(
        UUID insightId,
        UUID accusedMemberId) implements DomainEventMessage {
}
