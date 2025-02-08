package com.project.imdang.member.service.domain.ports.input.listener;


import com.project.imdang.domain.message.InsightCreatedCountRequestMessage;

public interface InsightCreatedCountMessageListener {
    void handle(InsightCreatedCountRequestMessage insightCreatedCountRequestMessage);
}
