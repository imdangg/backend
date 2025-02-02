package com.project.imdang.member.service.domain.ports.input.listener;

import com.project.imdang.domain.message.InsightAccusedRequestMessage;

public interface InsightAccusedRequestMessageListener {
    void handle(InsightAccusedRequestMessage insightAccusedRequestMessage);
}
