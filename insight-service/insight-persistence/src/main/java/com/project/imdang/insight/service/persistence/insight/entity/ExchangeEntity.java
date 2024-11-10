package com.project.imdang.insight.service.persistence.insight.entity;

import com.project.imdang.insight.service.domain.valueobject.ExchangeStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "exchange")
@Entity
public class ExchangeEntity {

    @Id
    private UUID id;
    // TODO - CHECK : 복합키 가능
    private UUID requestMemberId;
    private UUID requestMemberInsightId;
    private UUID requestedInsightId;

    private ZonedDateTime requestedAt;
    private ZonedDateTime respondedAt;

    @Enumerated(EnumType.STRING)
    private ExchangeStatus status;
}
