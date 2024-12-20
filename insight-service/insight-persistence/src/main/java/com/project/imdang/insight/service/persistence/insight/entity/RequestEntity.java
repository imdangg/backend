package com.project.imdang.insight.service.persistence.insight.entity;

import com.project.imdang.insight.service.domain.valueobject.RequestStatus;
import com.project.imdang.insight.service.persistence.insight.converter.UUIDStringConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Id
    private UUID id;

    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID requestMemberId;
    private Long memberCouponId;

    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID requestedInsightId;

    // TODO - CHECK : vs createdAt
    private ZonedDateTime requestedAt;
    private ZonedDateTime respondedAt;
    private RequestStatus status;
}
