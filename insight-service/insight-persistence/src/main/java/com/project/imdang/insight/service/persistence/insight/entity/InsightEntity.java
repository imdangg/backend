package com.project.imdang.insight.service.persistence.insight.entity;

import com.project.imdang.insight.service.domain.valueobject.Access;
import com.project.imdang.insight.service.domain.valueobject.Address;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.ComplexEnvironment;
import com.project.imdang.insight.service.domain.valueobject.ComplexFacility;
import com.project.imdang.insight.service.domain.valueobject.FavorableNews;
import com.project.imdang.insight.service.domain.valueobject.Infra;
import com.project.imdang.insight.service.domain.valueobject.VisitMethod;
import com.project.imdang.insight.service.domain.valueobject.VisitTime;
import com.project.imdang.insight.service.persistence.insight.converter.ComplexEnvironmentConverter;
import com.project.imdang.insight.service.persistence.insight.converter.ComplexFacilityConverter;
import com.project.imdang.insight.service.persistence.insight.converter.FavorableNewsConverter;
import com.project.imdang.insight.service.persistence.insight.converter.InfraConverter;
import com.project.imdang.insight.service.persistence.insight.converter.VisitMethodSetConverter;
import com.project.imdang.insight.service.persistence.insight.converter.VisitTimeSetConverter;
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

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Set;
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

    private String mainImage;
    private String title;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "siDo", column = @Column(name = "address_si_do")),
            @AttributeOverride(name = "siGunGu", column = @Column(name = "address_si_gun_gu")),
            @AttributeOverride(name = "eupMyeonDong", column = @Column(name = "address_eup_myeon_dong")),
            @AttributeOverride(name = "roadName", column = @Column(name = "address_road_name")),
            @AttributeOverride(name = "buildingNumber", column = @Column(name = "address_building_number")),
            @AttributeOverride(name = "detail", column = @Column(name = "address_detail")),
    })
    private Address address;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "complex_name")),
    })
    private ApartmentComplex apartmentComplex;

    private LocalDate visitAt;

    @Convert(converter = VisitTimeSetConverter.class)
    private Set<VisitTime> visitTimes;
    @Convert(converter = VisitMethodSetConverter.class)
    private Set<VisitMethod> visitMethods;
    @Enumerated(EnumType.STRING)
    private Access access;

    private String summary;

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

    private int recommendedCount;
    private int accusedCount;
    private int viewCount;

    private int score;

    // TODO - BaseEntity?
    private ZonedDateTime createdAt;
    // TODO - updatedAt;
}
