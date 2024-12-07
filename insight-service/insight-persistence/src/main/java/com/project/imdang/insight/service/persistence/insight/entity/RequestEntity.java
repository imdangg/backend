package com.project.imdang.insight.service.persistence.insight.entity;

import com.project.imdang.insight.service.domain.valueobject.RequestStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "request")
@Entity
public class RequestEntity {

    @Id
    private UUID id;
    private UUID requestMemberId;
    private Long memberCouponId;
    private UUID requestedInsightId;

    // TODO - CHECK : vs createdAt
    private ZonedDateTime requestedAt;
    private ZonedDateTime respondedAt;
    private RequestStatus status;
}
