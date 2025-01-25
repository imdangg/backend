package com.project.imdang.insight.service.persistence.insight.repository;

import com.project.imdang.insight.service.persistence.insight.entity.InsightEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public final class InsightSpecification {

    public static Specification<InsightEntity> equalsMemberId(String memberId) {
        return (Root<InsightEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (memberId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("memberId"), memberId);
        };
    }

    public static Specification<InsightEntity> equalsSiGunGu(String siGunGu) {
        return (Root<InsightEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (siGunGu == null || siGunGu.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("address").get("siGunGu"), siGunGu);
        };
    }

    public static Specification<InsightEntity> equalsEupMyeonDong(String eupMyeonDong) {
        return (Root<InsightEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (eupMyeonDong == null || eupMyeonDong.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("address").get("eupMyeonDong"), eupMyeonDong);
        };
    }

    public static Specification<InsightEntity> equalsApartmentComplexName(String apartmentComplexName) {
        return (Root<InsightEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (apartmentComplexName == null || apartmentComplexName.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("apartmentComplex").get("name"), apartmentComplexName);
        };
    }
}
