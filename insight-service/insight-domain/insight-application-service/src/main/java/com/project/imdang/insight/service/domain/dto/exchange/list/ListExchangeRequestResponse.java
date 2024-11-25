package com.project.imdang.insight.service.domain.dto.exchange.list;

import lombok.Getter;

@Getter
public class ListExchangeRequestResponse {
    private ListPendingRequestExchangeResponse pendingList;
    private ListRejectExchangeRequestResponse rejectedList;
    private ListAcceptExchangeRequestResponse acceptedList;

    public ListExchangeRequestResponse(ListPendingRequestExchangeResponse pendingList, ListRejectExchangeRequestResponse rejectedList, ListAcceptExchangeRequestResponse acceptedList) {
        this.pendingList = pendingList;
        this.rejectedList = rejectedList;
        this.acceptedList = acceptedList;
    }
}
