package com.project.imdang.member.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "coupon")
@Entity
public class CouponEntity {

    @Id
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;
    private String name;
    private String expirationDate;
}
