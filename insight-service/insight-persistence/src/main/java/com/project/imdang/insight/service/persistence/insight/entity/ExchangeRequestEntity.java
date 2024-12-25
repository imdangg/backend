package com.project.imdang.insight.service.persistence.insight.entity;

import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
import jakarta.persistence.Column;
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
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "exchange_request")
@Entity
public class ExchangeRequestEntity {

    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Id
    private UUID id;

    // TODO - CHECK : 복합키 가능
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID requestMemberId;

    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID requestMemberInsightId;
    private Long requestMemberSnapshotId;
    // OR
    private Long memberCouponId;

    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID requestedInsightId;
    private Long requestedSnapshotId;

    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID requestedMemberId;

    private ZonedDateTime requestedAt;
    private ZonedDateTime respondedAt;

    @Enumerated(EnumType.STRING)
    private ExchangeRequestStatus status;
}
