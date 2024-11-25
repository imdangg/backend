package com.project.imdang.insight.service.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExchangeRequestRejectedEvent {
    private String memberId;
}
