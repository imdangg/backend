package com.project.imdang.member.persistence.entity;


import com.project.imdang.domain.valueobject.CouponId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member_coupon")
@Entity
public class MemberCouponEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID couponId;

    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID memberId;

    //현재는 사용기한 없음
    //private ZonedDateTime expiredAt;

    private String remark;  // reason
    @NotNull
    private ZonedDateTime createdAt;

    @NotNull
    private Boolean used;
    private ZonedDateTime usedAt;

}
