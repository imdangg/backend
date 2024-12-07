package com.project.imdang.insight.service.persistence.insight.repository;

import com.project.imdang.insight.service.domain.entity.Insight;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public final class InsightSpecification {

    public static Specification<Insight> equalsMemberId(UUID memberId) {
        return (Root<Insight> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (memberId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("memberId"), memberId);
        };
    }

    public static Specification<Insight> equalsSiGunGu(String siGunGu) {
        return (Root<Insight> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (siGunGu == null || siGunGu.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("address").get("siGunGu"), siGunGu);
        };
    }

    public static Specification<Insight> equalsDong(String dong) {
        return (Root<Insight> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (dong == null || dong.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("address").get("dong"), dong);
        };
    }

    public static Specification<Insight> equalsApartmentComplex(String apartComplexName) {
        return (Root<Insight> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (apartComplexName == null || apartComplexName.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("apartComplex").get("name"), apartComplexName);
        };
    }
}
