package com.project.imdang.insight.service.persistence.insight.entity;

import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "exchange_request")
@Entity
public class ExchangeRequestEntity {

    @Id
    private UUID id;
    // TODO - CHECK : 복합키 가능
    private UUID requestMemberId;
    private UUID requestMemberInsightId;
    private UUID requestedInsightId;
    private UUID requestedMemberId;

    private ZonedDateTime requestedAt;
    private ZonedDateTime respondedAt;

    @Enumerated(EnumType.STRING)
    private ExchangeRequestStatus status;
}
