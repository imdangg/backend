package com.project.imdang.member.persistence.entity;

import com.project.imdang.member.service.domain.valueobject.Gender;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import jakarta.persistence.*;
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

    private String authId;
    @Enumerated(EnumType.STRING)
    private OAuthType authType;

    private String nickname;
    private String birthDate;
    private Gender gender;
}
