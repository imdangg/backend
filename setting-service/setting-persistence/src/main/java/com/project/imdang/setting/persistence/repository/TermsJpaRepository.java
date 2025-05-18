package com.project.imdang.setting.persistence.repository;

import com.project.imdang.setting.persistence.entity.TermsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsJpaRepository extends JpaRepository<TermsEntity, Long> {
}
