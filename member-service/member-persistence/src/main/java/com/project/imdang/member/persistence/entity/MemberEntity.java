package com.project.imdang.member.persistence.entity;

import com.project.imdang.member.service.domain.valueobject.Gender;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
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

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "member")
@Entity
public class MemberEntity {

    @Id
    private UUID id;

    private String authId;
    @Enumerated(EnumType.STRING)
    private OAuthType authType;

    private String nickname;
    private String birthDate;
    private Gender gender;

    private int insightCount;
    private int exchangeCount;
}
