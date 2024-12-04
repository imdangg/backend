package com.project.imdang.insight.service.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ExchangeRequestAcceptedEvent {
    private UUID insightId;
    private UUID requestMemberId;
}
