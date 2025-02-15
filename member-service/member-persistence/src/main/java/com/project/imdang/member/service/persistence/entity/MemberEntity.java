package com.project.imdang.member.service.persistence.entity;

import com.project.imdang.member.service.domain.valueobject.Gender;
import com.project.imdang.member.service.domain.valueobject.MemberStatus;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import com.project.imdang.member.service.domain.valueobject.PenaltyPeriod;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

    @NotNull
    private String authId;

    @Enumerated(EnumType.STRING)
    private OAuthType authType;

    private String nickname;
    private String birthDate;
    private Gender gender;
    private String deviceToken;

    private int insightCount;
    private int exchangeCount;
    private int rejectedCount;

    private String refreshToken;
    private Boolean isLogin;
    private Boolean isDeleted;
    private Boolean isCouponReceived;

    private int accusedCount;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "from", column = @Column(name = "penalty_from")),
            @AttributeOverride(name = "to", column = @Column(name = "penalty_to"))
    })
    private PenaltyPeriod penaltyPeriod;
}
