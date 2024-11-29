package com.project.imdang.insight.service.domain.mapper;

import com.project.imdang.insight.service.domain.dto.insight.request.RequestInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.request.RequestInsightResponse;
import com.project.imdang.insight.service.domain.entity.Request;
import org.springframework.stereotype.Component;

@Component
public class RequestDataMapper {

    public Request requestInsightCommandToRequest(RequestInsightCommand requestInsightCommand) {
        return Request.builder()

                .build();
    }

    public RequestInsightResponse requestToRequestInsightResponse(Request request) {
        return new RequestInsightResponse();
    }
}
