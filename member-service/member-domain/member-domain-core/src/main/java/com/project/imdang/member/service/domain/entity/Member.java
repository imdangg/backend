package com.project.imdang.member.service.domain.entity;

import com.project.imdang.domain.entity.AggregateRoot;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.valueobject.AccusePenaltyPolicy;
import com.project.imdang.member.service.domain.valueobject.Gender;
import com.project.imdang.member.service.domain.valueobject.MemberStatus;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import com.project.imdang.member.service.domain.valueobject.Penalty;
import com.project.imdang.member.service.domain.valueobject.PenaltyPeriod;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Member extends AggregateRoot<MemberId> {

    private String nickname;
    private String birthDate;
    private Gender gender;
    private String deviceToken;

    private String oAuthId;
    private OAuthType oAuthType;
    private int exchangeCount;
    private int insightCount;
    // TODO - CHECK : 데이터를 쌓아서 GROUP BY로?
    private int rejectedCount;

    private int accusedCount;
    private MemberStatus status;
    private PenaltyPeriod penaltyPeriod;

    public static Member createNewMember(String oAuthId, OAuthType oAuthType) {
        return Member.builder()
                .id(new MemberId(UUID.randomUUID()))
                .oAuthId(oAuthId)
                .oAuthType(oAuthType)
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
                  int exchangeCount,
                  int insightCount,
                  int rejectedCount,
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
        this.exchangeCount = exchangeCount;
        this.insightCount = insightCount;
        this.rejectedCount = rejectedCount;
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

    public void increaseAccusedCount(AccusePenaltyPolicy accusePenaltyPolicy) {
        this.accusedCount++;
        // TODO - 배치
//        accusePenaltyPolicy.apply(this);
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
}
