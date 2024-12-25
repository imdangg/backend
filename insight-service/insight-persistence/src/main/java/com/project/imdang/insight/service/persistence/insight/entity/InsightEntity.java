package com.project.imdang.insight.service.persistence.insight.entity;

import com.project.imdang.insight.service.domain.valueobject.Access;
import com.project.imdang.insight.service.domain.valueobject.Address;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.ComplexEnvironment;
import com.project.imdang.insight.service.domain.valueobject.ComplexFacility;
import com.project.imdang.insight.service.domain.valueobject.FavorableNews;
import com.project.imdang.insight.service.domain.valueobject.Infra;
import com.project.imdang.insight.service.domain.valueobject.VisitMethod;
import com.project.imdang.insight.service.persistence.insight.converter.ComplexEnvironmentConverter;
import com.project.imdang.insight.service.persistence.insight.converter.ComplexFacilityConverter;
import com.project.imdang.insight.service.persistence.insight.converter.FavorableNewsConverter;
import com.project.imdang.insight.service.persistence.insight.converter.InfraConverter;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
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
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "insight")
@Entity
public class InsightEntity {

    // TODO - CHECK : BINARY VS CHAR
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Id
    private UUID id;

    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID memberId;

    @Embedded
    private Address address;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "key", column = @Column(name = "complex_key")),
            @AttributeOverride(name = "name", column = @Column(name = "complex_name")),
    })
    private ApartmentComplex apartmentComplex;

    private String title;
    private String contents;

    private String mainImage;
    private String summary;

    private ZonedDateTime visitAt;

    @Enumerated(EnumType.STRING)
    private VisitMethod visitMethod;
    @Enumerated(EnumType.STRING)
    private Access access;

    // TODO - CHECK : 한번에 Convert?
    @Convert(converter = InfraConverter.class)
    @Column(columnDefinition = "json")
    private Infra infra;

    @Convert(converter = ComplexEnvironmentConverter.class)
    @Column(columnDefinition = "json")
    private ComplexEnvironment complexEnvironment;

    @Convert(converter = ComplexFacilityConverter.class)
    @Column(columnDefinition = "json")
    private ComplexFacility complexFacility;

    @Convert(converter = FavorableNewsConverter.class)
    @Column(columnDefinition = "json")
    private FavorableNews favorableNews;

//    private Boolean isDeleted;

    private int accusedCount;
    private int recommendedCount;
    private int viewCount;

    private int score;

    // TODO - BaseEntity?
    private ZonedDateTime createdAt;
    // TODO - updatedAt;
}
