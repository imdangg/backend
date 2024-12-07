package com.project.imdang.insight.service.persistence.insight.mapper;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.Request;
import com.project.imdang.insight.service.domain.valueobject.RequestId;
import com.project.imdang.insight.service.persistence.insight.entity.RequestEntity;
import org.springframework.stereotype.Component;

@Component
public class RequestPersistenceMapper {

    public RequestEntity requestToRequestEntity(Request request) {
        return RequestEntity.builder()
                .id(request.getId().getValue())
                .requestMemberId(request.getRequestMemberId().getValue())
                .memberCouponId(request.getMemberCouponId().getValue())
                .requestedInsightId(request.getRequestedInsightId().getValue())
                .requestedAt(request.getRequestedAt())
                .respondedAt(request.getRespondedAt())
                .status(request.getStatus())
                .build();
    }

    public Request requestEntityToRequest(RequestEntity requestEntity) {
        return Request.builder()
                .id(new RequestId(requestEntity.getId()))
                .requestMemberId(new MemberId(requestEntity.getRequestMemberId()))
                .memberCouponId(new MemberCouponId(requestEntity.getMemberCouponId()))
                .requestedInsightId(new InsightId(requestEntity.getRequestedInsightId()))
                .requestedAt(requestEntity.getRequestedAt())
                .respondedAt(requestEntity.getRespondedAt())
                .status(requestEntity.getStatus())
                .build();
    }
}
