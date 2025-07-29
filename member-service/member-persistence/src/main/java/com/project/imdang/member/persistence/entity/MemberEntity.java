package com.project.imdang.member.persistence.entity;

import com.project.imdang.common.domain.valueobject.*;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "member")
@Entity
public class MemberEntity {

    @Id
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    private String oAuthId;
    @Enumerated(EnumType.STRING)
    private OAuthProvider oAuthProvider;

    private String nickname;
    private String birthDate;
    private Gender gender;
    private String deviceToken;

//    private Long insightCount;

    private String refreshToken;
    private Boolean isLogin;
    private Boolean isDeleted;

    private LocalDate latestInsightCreateDate;
    private Long accusedCount;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    private Purpose purpose;
    @Enumerated(EnumType.STRING)
    private Budget budget;
    @Enumerated(EnumType.STRING)
    private MonthIncome monthIncome;

    private String firstPriority;
    private String secondPriority;
    private String thirdPriority;

    private String interestDistrict;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "from", column = @Column(name = "penalty_from")),
            @AttributeOverride(name = "to", column = @Column(name = "penalty_to"))
    })
    private PenaltyPeriod penaltyPeriod;
}
