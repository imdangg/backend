package com.project.imdang.setting.service.persistence.terms.repository;

import com.project.imdang.setting.service.persistence.terms.entity.TermsAgreementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsAgreementJpaRepository extends JpaRepository<TermsAgreementEntity, Long> {
}
