package com.project.imdang.insight.service.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExchangeRequestAcceptedEvent {
    private String memberId;
}
