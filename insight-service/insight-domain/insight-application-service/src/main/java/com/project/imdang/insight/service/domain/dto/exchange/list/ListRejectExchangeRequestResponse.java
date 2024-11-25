package com.project.imdang.insight.service.domain.dto.exchange.list;

import com.project.imdang.insight.service.domain.dto.insight.list.InsightListResponse;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ListRejectExchangeRequestResponse {
    private List<InsightListResponse> rejectExchangeRequests;

    public ListRejectExchangeRequestResponse(List<InsightListResponse> rejectExchangeRequests) {
        this.rejectExchangeRequests = rejectExchangeRequests;
    }
}
