package com.project.imdang.insight.service.persistence.insight.repository;

import com.project.imdang.insight.service.persistence.insight.entity.InsightEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

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

    public static Specification<InsightEntity> equalsDong(String dong) {
        return (Root<InsightEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (dong == null || dong.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("address").get("dong"), dong);
        };
    }

    public static Specification<InsightEntity> equalsApartmentComplex(String apartComplexName) {
        return (Root<InsightEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (apartComplexName == null || apartComplexName.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("apartmentComplex").get("name"), apartComplexName);
        };
    }
}
