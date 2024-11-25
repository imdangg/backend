package com.project.imdang.insight.service.domain.dto.exchange.list;

import com.project.imdang.insight.service.domain.dto.insight.list.InsightListResponse;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ListAcceptExchangeRequestResponse {
    private List<InsightListResponse> acceptExchangeRequests;

    public ListAcceptExchangeRequestResponse(List<InsightListResponse> acceptExchangeRequests) {
        this.acceptExchangeRequests = acceptExchangeRequests;
    }
}
