package com.project.imdang.insight.service.persistence.insight.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "insight")
@Entity
public class InsightEntity {

    @Id
    private UUID id;
//    private UUID originalId;
//    private int version;

    private UUID memberId;

}
