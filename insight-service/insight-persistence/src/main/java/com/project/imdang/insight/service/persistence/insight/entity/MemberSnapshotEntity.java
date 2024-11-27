package com.project.imdang.insight.service.persistence.insight.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "member_snapshot")
@Entity
public class MemberSnapshotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // TODO - CHECK : 복합키
    private UUID memberId;
    private Long snapshotId;

    private ZonedDateTime createdAt;
}
