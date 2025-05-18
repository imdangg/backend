package com.project.imdang.member.domain.entity;

import com.project.imdang.common.domain.entity.AggregateRoot;
import com.project.imdang.common.domain.valueobject.Gender;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.MemberStatus;
import com.project.imdang.common.domain.valueobject.OAuthType;
import com.project.imdang.common.domain.valueobject.Penalty;
import com.project.imdang.common.domain.valueobject.PenaltyPeriod;
import com.project.imdang.member.domain.valueobject.AccusePenaltyPolicy;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Member extends AggregateRoot<MemberId> {

    private final String oAuthId;
    private final OAuthType oAuthType;

    private String nickname;
    private String birthDate;
    private Gender gender;
    private String deviceToken;

    private int insightCount;

    private String refreshToken;
    private Boolean isLogin;
    private Boolean isDeleted;
  
    private int accusedCount;
    private MemberStatus status;
    private PenaltyPeriod penaltyPeriod;

    public static Member createNewMember(String oAuthId, OAuthType oAuthType) {
        return Member.builder()
                .id(new MemberId(UUID.randomUUID()))
                .oAuthId(oAuthId)
                .oAuthType(oAuthType)
                .isDeleted(Boolean.FALSE)
                .isLogin(Boolean.TRUE)
                .status(MemberStatus.ACTIVE)
                .build();
    }

    @Builder
    public Member(MemberId id,
                  String nickname,
                  String birthDate,
                  Gender gender,
                  String deviceToken,
                  String oAuthId,
                  OAuthType oAuthType,
                  int insightCount,
                  String refreshToken,
                  Boolean isLogin, 
                  Boolean isDeleted,
                  int accusedCount,
                  MemberStatus status,
                  PenaltyPeriod penaltyPeriod) {
        setId(id);
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.deviceToken = deviceToken;
        this.oAuthId = oAuthId;
        this.oAuthType = oAuthType;
        this.insightCount = insightCount;
        this.refreshToken = refreshToken;
        this.isLogin = isLogin;
        this.isDeleted = isDeleted;
        this.accusedCount = accusedCount;
        this.status = status;
        this.penaltyPeriod = penaltyPeriod;
    }

    public void join(String nickname, String birthDate, Gender gender, String deviceToken) {
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.deviceToken = deviceToken;
    }

    public void logout() {
        this.isLogin = Boolean.FALSE;
        this.refreshToken = null;
    }

    public void withdraw() {
        this.isDeleted = Boolean.TRUE;
        this.refreshToken = null;
        this.deviceToken = null;
    }

    public void storeRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void accuse(AccusePenaltyPolicy accusePenaltyPolicy) {
        this.accusedCount++;
        accusePenaltyPolicy.apply(this);
    }

    public void applyPenalty(Penalty penalty) {
        this.status = penalty.getMemberStatus();
        this.penaltyPeriod = PenaltyPeriod.during(penalty.getDays());
    }

    // TODO - 배치
    public void liftPenalty() {
        this.status = MemberStatus.ACTIVE;
        this.penaltyPeriod = null;
    }

    public Member increaseInsightCount() {
        this.insightCount++;
        return this;
    }
}
