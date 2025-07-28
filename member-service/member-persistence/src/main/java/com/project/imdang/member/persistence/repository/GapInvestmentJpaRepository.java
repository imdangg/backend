package com.project.imdang.member.persistence.repository;


import com.project.imdang.member.persistence.entity.GapInvestmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GapInvestmentJpaRepository extends JpaRepository<GapInvestmentEntity, Long> {
}
