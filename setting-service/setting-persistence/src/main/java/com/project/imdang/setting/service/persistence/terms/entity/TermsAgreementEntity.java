package com.project.imdang.setting.service.persistence.terms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "terms_agreement")
@Entity
public class TermsAgreementEntity {

    @Id
    private Long id;

    // TODO - CHECK : 복합키
    private Long termsId;
    private UUID memberId;
    private ZonedDateTime createdAt;
}
