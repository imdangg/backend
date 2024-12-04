package com.project.imdang.member.persistence.entity;

import com.project.imdang.member.service.domain.valueobject.Gender;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class MemberEntity {

    @Id
    private UUID id;

    private String oAuthId;

    @Enumerated
    private OAuthType oAuthType;

    private String nickname;

    private String birthDate;

    private Gender gender;

    @Builder
    public MemberEntity(UUID id, String oAuthId, OAuthType oAuthType, String nickname, String birthDate, Gender gender) {
        this.id = id;
        this.oAuthId = oAuthId;
        this.oAuthType = oAuthType;
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.gender = gender;
    }
}
