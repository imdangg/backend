package com.project.imdang.setting.persistence.repository;

import com.project.imdang.setting.persistence.entity.TermsAgreementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsAgreementJpaRepository extends JpaRepository<TermsAgreementEntity, Long> {
}
