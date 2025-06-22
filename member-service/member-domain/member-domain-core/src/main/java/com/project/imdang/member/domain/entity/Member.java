package com.project.imdang.member.domain.entity;

import com.project.imdang.common.domain.entity.AggregateRoot;
import com.project.imdang.common.domain.valueobject.Gender;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.MemberStatus;
import com.project.imdang.common.domain.valueobject.OAuthProvider;
import com.project.imdang.common.domain.valueobject.Penalty;
import com.project.imdang.common.domain.valueobject.PenaltyPeriod;
import com.project.imdang.member.domain.valueobject.AccusePenaltyPolicy;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class Member extends AggregateRoot<MemberId> {

    private final String oAuthId;
    private final OAuthProvider oAuthProvider;

    private String nickname;
    private String birthDate;
    private Gender gender;
    private String deviceToken;

    private LocalDate latestInsightCreateDate; //가장 최근 인사이트 게시물 등록일자
    private String refreshToken;

    private Boolean isLogin;
    private Boolean isDeleted;
  
    private int accusedCount;
    private MemberStatus status;
    private PenaltyPeriod penaltyPeriod;

    public static Member createNewMember(String oAuthId, OAuthProvider oAuthProvider) {
        return Member.builder()
                .id(new MemberId(UUID.randomUUID()))
                .oAuthId(oAuthId)
                .oAuthProvider(oAuthProvider)
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
                  OAuthProvider oAuthProvider,
                  LocalDate latestInsightCreateDate,
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
        this.oAuthProvider = oAuthProvider;
        this.latestInsightCreateDate = latestInsightCreateDate;
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

    public Member updateInsightCreateDate() {
        this.latestInsightCreateDate = LocalDate.now();
        return this;
    }
}
