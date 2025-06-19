package com.project.imdang.insight.persistence.entity;

import com.project.imdang.insight.domain.entity.InsightImage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "insight_image")
@Entity
public class InsightImageEntity {

    @Id
    @Column(name = "image", length = 255, nullable = false)
    private String image;

    @Column(name = "insight_id", columnDefinition = "char(36)", nullable = false)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID insightId;

    @Column(name = "type", nullable = false)
    private int type;

    @Column(name = "sort_num", nullable = false)
    private int sortNum;

    @Column(name = "created_at", columnDefinition = "datetime(6)", nullable = false)
    private ZonedDateTime createdAt;
}
