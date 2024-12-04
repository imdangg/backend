package com.project.imdang.insight.service.domain.mapper;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.insight.request.RequestInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.request.RequestInsightResponse;
import com.project.imdang.insight.service.domain.entity.Request;
import org.springframework.stereotype.Component;

@Component
public class RequestDataMapper {

    public Request requestInsightCommandToRequest(RequestInsightCommand requestInsightCommand) {
        return Request.createNewRequest(
                new MemberId(requestInsightCommand.getRequestMemberId()),
                new MemberCouponId(requestInsightCommand.getMemberCouponId()),
                new InsightId(requestInsightCommand.getInsightId())
        );
    }

    public RequestInsightResponse requestToRequestInsightResponse(Request request) {
        return RequestInsightResponse.builder()
                .insightId(request.getRequestedInsightId().getValue())
                .build();
    }
}
