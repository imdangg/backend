package com.project.imdang.insight.service.domain.dto.exchange.list;

import com.project.imdang.insight.service.domain.dto.insight.list.InsightListResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class ListPendingRequestExchangeResponse {
    //교환 ID
    private List<InsightListResponse> pendingExchangeRequests;

    public ListPendingRequestExchangeResponse(List<InsightListResponse> pendingExchangeRequests) {
        this.pendingExchangeRequests = pendingExchangeRequests;
    }
}
